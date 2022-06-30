from collections import ChainMap, OrderedDict
import json
import instaloader
import sys

def loginUserAndPassword(username, password):
    try:
        L = instaloader.Instaloader()
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








def getFeeds(username):
    loader = instaloader.Instaloader()
    loader.load_session_from_file(username)
    # s = loader.download_feed_posts(max_count=20, fast_update=True, post_filter=lambda post: post.viewer_has_liked)
    # L = loader.get_feed_posts()
    profile = instaloader.Profile.from_username(loader.context, username)
    # profile = instaloader.load_structure(loader.context, 'Post')
    # profile = instaloader.load_structure(loader.context, {instaloader.Post, instaloader.Profile, instaloader.StoryItem})

    

    posts = profile.get_posts()


    for post in posts:
        print(post)
    
    # for item in profile:
        # return item

    # try:
    #     print(s)

    # except instaloader.exceptions.LoginRequiredException:
    #     print('{"code":400,"status":"LoginRequired"}')

    # except BaseException as err:
    #     print('{"code":400,"status":"Unexpected Error: ' + str(err) + '"}')




def getProfileByUsername(username):
    loader = instaloader.Instaloader()
    # loader.load_session_from_file(username)
    loader.login(username, '09383890149ppp')
    profile = instaloader.Profile.from_username(loader.context, username)

    profile_pic_url = profile.get_profile_pic_url()

    posts           = profile.get_posts()
    new_post_list   = list(range(posts.count))
    parent_object   = OrderedDict()
    newPost         = OrderedDict()

    for post in posts:
        newPost["image"]                = str(post.url)
        newPost["comments_count"]       = post.comments
        newPost["caption"]              = str(post.caption)
        newPost["likes"]                = post.likes
        newPost["shortcode"]            = str(post.shortcode)
        newPost["video_view_count"]     = post.video_view_count
        newPost["video_duration"]       = str(post.video_duration)
        newPost["caption_mentions"]     = post.caption_mentions
        newPost["is_video"]             = post.is_video
        newPost["pcaption"]             = str( post.pcaption)
        newPost["profile"]              = str(post.profile)
        newPost["video_url"]            = str(post.video_url)
        # newPost["comments"]           = post.get_comments()
        # newPost["_full_metadata"]           = post._full_metadata
        # newPost["_node"]                    = post._node
        new_post_list.append(newPost)


    parent_object["profile_pic_url"] = profile_pic_url
    parent_object["posts"]           = new_post_list

    return json.dumps(parent_object)



        # x = post.replace("<Post ", "")
        # x = post.replace(">", "")
        # json += '{"post_url": "https://www.instagram.com/p/' + x + '/?__a=1"},'
        # print(post.url)
    # json += ']'

    # for item in profile:
        # return item

    # try:
    #     print(s)

    # except instaloader.exceptions.LoginRequiredException:
    #     print('{"code":400,"status":"LoginRequired"}')

    # except BaseException as err:
    #     print('{"code":400,"status":"Unexpected Error: ' + str(err) + '"}')