# StyledBy Overview
# Description 
A comprehensive application for individuals to anonymously review and seek opinions on local service providers, fostering a community-driven approach to sharing experiences with barbers, stylists, nail techs, and more. The app creates a space for users to rate and provide unbiased feedback on these professionals, facilitating informed decisions for others seeking quality services. 

# App Evaluation
- **Category**: Lifestyle/Social Forum & Community Chats
- **Story**: Allows users to anonymously review local service providers and share/inquire about experiences and recommendations.
- **Market**: Any individual in the local community interested in cosmetic services could choose to use this app.
- **Habit**: This app could be used as often as the user wanted depending on how frequently they use local services.
- **Scope**: This app would start off serving local (Tallahassee) citizens and will then branch out to support other local communities throughout the country.

# Product Spec
# 1. User Stories (Required and Optional)
**Required Must-have Stories**
- User login
- Homepage feed (recent reviews and popular community forum posts)
- Review page (details about the service provider (business name, date of service, service type, proof of service (booking and finished work), social media/contact), rating, and descriptive review, pictures)
- User/Business Profile
- Community Forum
- Settings (Accessibility, Notification, General, etc.
- Notifications for: business owners when their business is mentioned;  users who asked a question on the forum and received a response; flagged or reported posts/reviews
- Search for local businesses and see their reviews

**Optional Nice-to-have Stories**
- Service Verification  ( confirm bookings)
- Updates on review interactions (likes, comments, replies)
- Automated forum responses based on generalized reviews

# 2. Screens
- Login
- Register - User signs up or logs into their account
  - Upon Download/Reopening of the application, the user is prompted to log in to         gain access to their profile information.
- Add Page
  - Where users write reviews & forums
  - Hovering button on all tabs that lets you select review or forum to write
- Add Review + Add Forum (2 pages)
- Homepage - Recent reviews/ popular community forum posts
  - “Leave a Review” Option
  - Filter Options
- Notification Page - shows recent notifications users see
  - Search
    - Search a business and see their profile, recent reviews, and forums mentioning them
- Profile Screen
  - User - sees recent reviews, forum posts, and forum replies
  - Business
      - see mentioned reviews, forums, service type, overall rating
- Settings Screen
  - Top Right Icon in Profile
  - Lets people change language and app notification settings.

# 3. Navigation
**Tab Navigation** (Tab to Screen)
- Homepage
- Notifications
- Search
- Profile

**Optional:**
- Add Page
- Settings

**Flow Navigation** (Screen to Screen)
- Anywhere -> Enter Details & Review -> Confirm Honesty -> Submit a Review
- Anywhere ->  Enter Question -> Submit a Forum Post
- Notifications -> Click Single Review -> see notification details
- Homepage->(optional) Filter Forums -> Click answer button -> Answer a Forum Post
- Search -> type business -> toggle tabs -> click review - > View review details
- Forced Log-in -> Account creation if no log-in is available
- Profile -> Text field to be modified.
- Settings -> Toggle settings
