Group 8: songbyte
===


# songbyte

## Table of Contents
1. [Overview](#Overview)
1. [Product Spec](#Product-Spec)


## Overview
### Description
Allows the user to post songs anonymously to their location and nearby anonymous users will be able to listen and upvote

### App Evaluation
- **Category:** Social Networking / Music
- **Mobile:** Stictly a mobile app since location sharing and exploring other towns will be a central theme of the app.
- **Story:** The user will be allowed to share personal music anonymously without the fear of feeling awkward if the listener doesn't enjoy the song they posted. 
- **Market:** All user of all ages would use this app.
- **Habit:** Since the application would focus on being anonymous, it is not expected for the user to return as often as, say twitter, as they do not have any kind of reputation building involved.
- **Scope:** Location sharing is vital to the app working, after this the user will search for a song with a music API such as Spotify's API. The song is posted and then other anonymous users will rate. All data is stored in a database.

## Product Spec
### 1. User Stories (Required and Optional)

**Required Must-have Stories**

* User opens app and is shown the front page of all other song postings. 
* User can see their total upvotes on a separate page (we'll try to do this without having the user create a profile, to remain as anonymous as possible)
* Song posting page 
* Song posting reactions and comments 


### 2. Screen Archetypes

* Song posting screen
* Song reaction screen
* Local area view of other postings 
* Total points page
* 
### 3. Navigation

**Tab Navigation** (Tab to Screen)

* Post song
* See song reactions
* Points accumilated


**Flow Navigation** (Screen to Screen)
* Local song postings page -> Song details 
* Post song page -> entering the song name or url plus any other necessary info
* Total points -> see history of how other anonymous people like your postings 

## Schema 
### Models
#### Post

   | Property      | Type     | Description |
   | ------------- | -------- | ------------|
   | objectId      | String   | unique id for the user post (default field) |
   | image         | File     | thumbnail of youtube post |
   | caption       | String   | image caption by author or description from youtube video |
   | likesCount    | Number   | number of likes for the post |
   | createdAt     | DateTime | date when post is created (default field) but user should stay anonymous |

### Networking
#### List of network requests by screen
   - Home Feed Screen
      - (Read/GET) Query all posts made in app(GET all posts/songs)
      - (Create/POST) Create a new like on a post
      - (Delete) Delete existing like
      - (Create/POST) Create a new comment on a post
      - (Delete) Delete existing comment
   - Create Song Post screen
      - (Create/POST) Create a new song object
   - Profile Screen
      - (Read/GET) Query logged in user object
      - (Read/GET) All posts by logged in user
