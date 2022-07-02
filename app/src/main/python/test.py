from collections import OrderedDict
import json
import instaloader

username = 'hadi.notes'
to_pagination = 2
loader = instaloader.Instaloader(compress_json=False,  max_connection_attempts=1, request_timeout=300.0, download_pictures= False,download_videos=False)
profile = instaloader.Profile.from_username(loader.context, username)

per_page        = 7
to_pagination   *= per_page
from_pagination = to_pagination - per_page
posts           = profile.get_posts()
new_post_list   = list(range(0))
parent_object   = OrderedDict()
profile_object   = OrderedDict()
# profile_object["followers"]                 = profile.followers
# profile_object["followees"]                 = profile.followees
# profile_object["post_count"]                = posts.count
profile_object["is_private"]                = profile.is_private
# profile_object["biography"]                 = str(profile.biography)
# profile_object["followed_by_viewer"]        = profile.followed_by_viewer

for index, post in enumerate(posts):
    if(from_pagination <= index and to_pagination > index):
        new_post                         = OrderedDict()
        # new_post["image"]                = str(post.url)
        # new_post["comments_count"]       = post.comments
        # new_post["caption"]              = str(post.caption)
        # new_post["likes"]                = post.likes
        # new_post["shortcode"]            = str(post.shortcode)
        # new_post["video_view_count"]     = post.video_view_count
        # new_post["video_duration"]       = str(post.video_duration)
        new_post["is_video"]             = post.is_video
        # new_post["pcaption"]             = str(post.pcaption)
        # new_post["profile"]              = str(post.profile)
        # new_post["video_url"]            = str(post.video_url)
        # newPost["comments"]              = post.get_comments()
        new_post["OKKK"]       = post._node

        new_images_list   = list(range(0))
        
        # if(len(images) > 1):
        #     for index, image in enumerate(images):
        #         images_model            = OrderedDict()
        #         images_model["image_url"]  = images[index]['node']['display_url']
        #         images_model["is_video"]  = images[index]['node']['is_video']
        #         new_images_list.append(images_model)

        new_post["images"]       = new_images_list

        new_post_list.append(new_post)

    elif (to_pagination < index):     
        break

parent_object["status"]  = "success"
parent_object["posts"]   = new_post_list

print(json.dumps(parent_object))