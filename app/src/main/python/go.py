from collections import ChainMap, OrderedDict
import json
import instaloader

username = 'hadi.notes'

loader = instaloader.Instaloader()
loader.load_session_from_file(username)
# loader.login(username, '09383890149ppp')
profile = instaloader.Profile.from_username(loader.context, username)

profile_pic_url = profile.get_profile_pic_url()

posts           = profile.get_posts()
new_post_list   = list(range(posts.count))
parent_object   = OrderedDict()
newPost         = OrderedDict()

for post in posts:
    newPost["image"]                = str(post.url)
    # newPost["comments"]           = post.get_comments()
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
    # newPost["_full_metadata"]           = post._full_metadata
    # newPost["_node"]                    = post._node
    new_post_list.append(newPost)
    break

parent_object["profile_url"] = profile_pic_url
parent_object["posts"]       = new_post_list

print(json.dumps(parent_object))