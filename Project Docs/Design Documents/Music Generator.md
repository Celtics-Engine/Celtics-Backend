# [The Boston Celtics] Design Document

  

## Instructions

  

*Save a copy of this template for your team in the same folder that contains

this template.*

  

Procedurally generated audio for content creators



*You should take a look at the example design document in the same folder as

this template for more guidance on the types of information to capture, and the

level of detail to aim for.*

  

## *Project Title* Design

  

 ## 1. Problem Statement 

 Content creators get copywrite strikes on Youtube because they accidentally use trade marked audio

  
  

## 2. Top Questions to Resolve in Review  

*List the most important questions you have about your design, or things that
you are still debating internally that you might like help working through.

1. Is the time scope (2 weeks)
2. How do we create audio or use sampled audio to generate the tracks (scalaCollider)
3. How does audio work while sending and recieving over server (audio storage on server)
4. The algorithm for generation of good sounding music
6. Where does the abstraction of what should be generated stop
6. How do we layer music events and beats
7. How do you tell an algorthm where or when to break music theory rules
8. Question of Feature: How to generate two differen't music genres into one song (mix genre)
9. Question of Business: How do we deliver product to consumer (deliver the generated audio)
 

## 3. Use Cases

  

*This is where we work backwards from the customer and define what our customers

would like to do (and why). You may also include use cases for yourselves, or

for the organization providing the product to customers.*

  

U1. *As a [product] customer, I want to `<get copywrite free audio>` when I `<use the service>`*

  

## 4. Project Scope

  

intend to solve copywrite strikes on content creator videos

  

### 4.1. In Scope

  

*Which parts of the problem defined in Sections 1 and 2 will you solve with this

design?*

  

### 4.2. Out of Scope

  

*Based on your problem description in Sections 1 and 2, are there any aspects

you are not planning to solve? Do potential expansions or related problems occur

to you that you want to explicitly say you are not worrying about now? Feel free

to put anything here that you think your team can't accomplish in the unit, but

would love to do with more time.*

  

# 5. Proposed Architecture Overview

  

*Describe broadly how you are proposing to solve for the requirements you

described in Section 2.*

  

*This may include class diagram(s) showing what components you are planning to

build.*

  

*You should argue why this architecture (organization of components) is

reasonable. That is, why it represents a good data flow and a good separation of

concerns. Where applicable, argue why this architecture satisfies the stated

requirements.*

  

# 6. API

  

## 6.1. Public Models

  

*Define the data models your service will expose in its responses via your

*`-Model`* package. These will be equivalent to the *`PlaylistModel`* and

*`SongModel`* from the Unit 3 project.*

  

## 6.2. *First Endpoint*

  

*Describe the behavior of the first endpoint you will build into your service

API. This should include what data it requires, what data it returns, and how it

will handle any known failure cases. You should also include a sequence diagram

showing how a user interaction goes from user to website to service to database,

and back. This first endpoint can serve as a template for subsequent endpoints.

(If there is a significant difference on a subsequent endpoint, review that with

your team before building it!)*

  

*(You should have a separate section for each of the endpoints you are expecting

to build...)*

  

## 6.3 *Second Endpoint*

  

*(repeat, but you can use shorthand here, indicating what is different, likely

primarily the data in/out and error conditions. If the sequence diagram is

nearly identical, you can say in a few words how it is the same/different from

the first endpoint)*

  

# 7. Tables

  

*Define the DynamoDB tables you will need for the data your service will use. It

may be helpful to first think of what objects your service will need, then

translate that to a table structure, like with the *`Playlist` POJO* versus the

`playlists` table in the Unit 3 project.*

  

# 8. Pages

  

*Include mock-ups of the web pages you expect to build. These can be as

sophisticated as mockups/wireframes using drawing software, or as simple as

hand-drawn pictures that represent the key customer-facing components of the

pages. It should be clear what the interactions will be on the page, especially

where customers enter and submit data. You may want to accompany the mockups

with some description of behaviors of the page (e.g. “When customer submits the

submit-dog-photo button, the customer is sent to the doggie detail page”)*