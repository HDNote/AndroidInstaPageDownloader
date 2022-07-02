from collections import OrderedDict
import json
import instaloader


def getInstaloader():
    return instaloader.Instaloader(compress_json=False,  max_connection_attempts=1,
    request_timeout=300.0, download_pictures= False,download_videos=False)

def loginUserAndPassword(username, password):
    try:
        L = getInstaloader()
        L.login(username, password)
        L.save_session_to_file()
        return '{"code":200,"status":"Success"}'
 
    except instaloader.exceptions.InvalidArgumentException:
        return '{"code":404,"status":"Username not found"}'

    except instaloader.exceptions.BadCredentialsException:
        return '{"code":403,"status":"Wrong password"}'

    except instaloader.exceptions.ConnectionException:
        return '{"code":403,"status":"Connection Failed"}'

    except instaloader.exceptions.TwoFactorAuthRequiredException:
        return '{"code":400,"status":"Two Step Verification enabled on"}'

    except BaseException:
        return '{"code":400,"status":"Unexpected Error"}'




def getProfileByUsername(loged_username, username, to_pagination):
    try:
        loader = getInstaloader()
        loader.load_session_from_file(loged_username)
        profile = instaloader.Profile.from_username(loader.context, username)
        
        per_page        = 12
        to_pagination   *= per_page
        from_pagination = to_pagination - per_page

        posts           = profile.get_posts()
        new_post_list   = list(range(0))
        parent_object   = OrderedDict()

        profile_object   = OrderedDict()
        profile_object["profile_pic_url"]           = str(profile.get_profile_pic_url())
        profile_object["full_name"]                 = str(profile.full_name)
        profile_object["followers"]                 = profile.followers
        profile_object["followees"]                 = profile.followees
        profile_object["post_count"]                = posts.count
        profile_object["is_private"]                = profile.is_private
        profile_object["biography"]                 = str(profile.biography)
        profile_object["followed_by_viewer"]        = profile.followed_by_viewer

        for index, post in enumerate(posts):
            if(from_pagination <= index and to_pagination > index):
                new_post                         = OrderedDict()
                new_post["image"]                = str(post.url)
                new_post["comments_count"]       = post.comments
                new_post["caption"]              = str(post.caption)
                new_post["likes"]                = post.likes
                new_post["shortcode"]            = str(post.shortcode)
                new_post["video_view_count"]     = post.video_view_count
                new_post["video_duration"]       = str(post.video_duration)
                new_post["is_video"]             = post.is_video
                new_post["pcaption"]             = str(post.pcaption)
                new_post["profile"]              = str(post.profile)
                new_post["video_url"]            = str(post.video_url)

                new_images_list   = list(range(0))
                
                try:
                    images            = post._node["edge_sidecar_to_children"]['edges']
                    if(len(images) > 1):
                        for image in images:
                            images_model               = OrderedDict()
                            images_model["image_url"]  = image['node']['display_url']
                            images_model["is_video"]   = image['node']['is_video']
                            if(image['node']['is_video']):
                                images_model["video_url"]   = image['node']['video_url']
                            
                            new_images_list.append(images_model)
                except BaseException:
                    print("edge_sidecar_to_children")

                new_post["images"]       = new_images_list
                new_post_list.append(new_post)

            elif (to_pagination < index):     
                break


        parent_object["code"]    = 200
        parent_object["status"]  = "success"
        parent_object["profile"] = profile_object
        parent_object["posts"]   = new_post_list

        return json.dumps(parent_object)

    except instaloader.exceptions.ProfileNotExistsException:
        return '{"code":404,"status":"Profile doesnt exist!"}'

    except instaloader.exceptions.ConnectionException:
        return '{"code":403,"status":"Change your ip by turning off the Mobile Data"}'

    except BaseException as err:
        return '{"code":400,"status":"Unexpected Error"}'