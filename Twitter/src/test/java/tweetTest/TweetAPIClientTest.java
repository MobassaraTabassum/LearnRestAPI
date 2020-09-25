package tweetTest;

import io.restassured.response.ValidatableResponse;
import org.junit.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import tweet.TweetAPIClient;

import java.util.UUID;

public class TweetAPIClientTest {
    private TweetAPIClient tweetAPIClient;

    @BeforeClass
    public void setUpTweetAPI(){
        this.tweetAPIClient=new TweetAPIClient();
    }

    @Test
    public void testUserCanTweetSuccessfully(){
        // 1. user send a tweet
        String tweet="Hello I am practising automation "+ UUID.randomUUID().toString();
        ValidatableResponse response= this.tweetAPIClient.createTweet(tweet);
        // 2. Verify that the tweet was successful
        response.statusCode(200);
        String actualTweet= response.extract().body().path("text");
        Assert.assertEquals(tweet,actualTweet);
    }


    @Test
    public void testUserCanNotTweetTheSameTweetTwiceInARow(){
        // 1. user send a tweet
        // String tweet="We are learning RestAPI Automation and Tweet check"+ UUID.randomUUID().toString();
        String tweet="We are learning RestAPI Automation and Tweet check";
        ValidatableResponse response= this.tweetAPIClient.createTweet(tweet);
        // 2. Verify that the tweet was successful
        response.statusCode(200);

        System.out.println(response.extract().body().asString());
        String actualTweet= response.extract().body().path("text");
        Assert.assertEquals(tweet,actualTweet);
        // User send the same tweet again
        response= this.tweetAPIClient.createTweet(tweet);
        // Verify that the tweet was unsuccessful
        response.statusCode(403);
        //System.out.println(response.extract().body().asString());
        String expectedMessage = "Status is a duplicate.";
        String actualMessage = response.extract().body().path("errors[0].message");
        Assert.assertEquals(actualMessage, expectedMessage);
        Assert.assertNotSame("200", 403);
    }
    @Test(enabled = true)
    public void testDelete(){
        String tweet="Hello I am practising automation 4a93b62a-ab23-4242-bb5f-89de217fbf47442264f8-abc2-429e-a67d-282d5a5bb88c";
        ValidatableResponse response=this.tweetAPIClient.deleteTweet(1309362862289289218l);
        // Verify that the tweet was successfully deleted
        response.statusCode(200);
        String actualTweet=response.extract().body().path("text");
        Assert.assertEquals(tweet,actualTweet);
    }
    @Test(enabled = true)
    public void testReTweet(){
        String tweet="RT @BarackObama: This Labor Day, let’s thank all those who've kept our country going this year—nurses, teachers, delivery drivers, food service workers, and many more. We can honor them by building our system back even better—so that essential workers are treated like it, pandemic or not.";
        ValidatableResponse response=this.tweetAPIClient.reTweet(1303015313320050688l);
        // Verify that the tweet was successfully deleted
        response.statusCode(200);
        String actualTweet=response.extract().body().path("text");
        Assert.assertEquals(tweet,actualTweet);
    }


    @Test(enabled = true)
    public void testUnReTweet(){
        String tweet="Joe Biden more pro police than Trump.";
        ValidatableResponse response=this.tweetAPIClient.unReTweet(1308991499418431493l);
        response.statusCode(200);
        String actualTweet=response.extract().body().path("text");
        Assert.assertEquals(tweet,actualTweet);
    }


    @Test(enabled = true)
    public void testShowTweetID(){
            String tweet="We are learning RestAPI Automation and Tweet check";
            ValidatableResponse response=this.tweetAPIClient.showTweetID(1309358106808377344l);
            response.statusCode(200);
            System.out.println(response.extract().body().asString());
            String actualTweet = response.extract().body().path("text");
            Assert.assertEquals(tweet, actualTweet);
        }

    /**
     * Show tweet with invalid data
     */
    @Test(enabled = true)
    public void testShowTweetIDWithInvalidData(){
        String tweet="Today is cloudy.";
        ValidatableResponse response=this.tweetAPIClient.showTweetIDWithInvalidData(1309196682865840000l);
        System.out.println(response.extract().body().asString());
        int actualCode = response.extract().statusCode();
        Assert.assertEquals(404, actualCode);
    }



    @Test(enabled = true)
    public void FavoritesTweetID(){
        String tweet="We are learning RestAPI Automation and Tweet check";
//        ValidatableResponse response=this.tweetAPIClient.favoritesTweet(1308874571995664386L);
        ValidatableResponse response=this.tweetAPIClient.favoritesTweet(1309358106808377344l);
        response.statusCode(200);
        String actualTweet=response.extract().body().path("text");
        Assert.assertEquals(tweet,actualTweet);
    }

    @Test(enabled = true)
    public void unLikeFavoritesTweet(){
        String tweet="hey there";
        ValidatableResponse response=this.tweetAPIClient.unlikeFavoritesTweet(1307111166636969988L);
        response.statusCode(200);
        String actualTweet=response.extract().body().path("text");
        Assert.assertEquals(tweet,actualTweet);
    }
//group

    @Test(enabled =true)
    public void testGetUserTimeTweet(){
        String tweet="Joe Biden more pro police than Trump.";
        ValidatableResponse response= this.tweetAPIClient.getUserTimeTweet(1308991499418431493l);
        // 2. Verify that the tweet was successful
        response.statusCode(200);
        String actualTweet= response.extract().body().path("text");
        Assert.assertEquals(tweet,actualTweet);
    }



    @Test(enabled = true)
    public void testReadTweetID(){
        String expectedTweet="RestAPI Team @ 2";
        ValidatableResponse response=this.tweetAPIClient.readTweet(1308829567298285568l);
// Verify that the tweet was successfully deleted
        System.out.println(response.extract().body().asString());
        response.statusCode(200);
        String actualTweet=response.extract().body().path("text");
        Assert.assertEquals(expectedTweet,actualTweet);
    }

    @Test(enabled = true)
    public void testGetStatusLookUp1(){
        String expectedTweet="Let’s get me elected, just for the hell of it";
        ValidatableResponse response=this.tweetAPIClient.getStatusLookUp(1235809247977324544l);
        System.out.println(response.extract().body().asString());
        response.statusCode(200);
//        String actualTweet=response.extract().body().path("text");
//        Assert.assertEquals(expectedTweet,actualTweet);
    }
    /**
     * create Status LookUp with valid data
     */
    @Test(enabled = true)
    public void testGetStatusLookUp(){
        ValidatableResponse response=this.tweetAPIClient.getStatusLookUp(20,"MobassaraT");
        int actualResult=response.extract().statusCode();
        System.out.println(actualResult);
        System.out.println(response.extract().body().asString());
        Assert.assertEquals(200,actualResult);
    }
    /**
     * create Status LookUp with invalid data
     */
    @Test(enabled = true)
    public void testGetStatusLookUpWithInvalidData(){
        ValidatableResponse response=this.tweetAPIClient.getStatusLookUpWithInvalidData(20,"MobassaraT");
        int actualResult=response.extract().statusCode();
        Assert.assertEquals(404,actualResult);
    }


    @Test(enabled = true)
    public void TestCreateTweetFavoritesWithInvalidEndPoint(){
        String tweet="RestAPI Team @ 2";
        ValidatableResponse response=this.tweetAPIClient.createTweetWithWrongFavoritesInvalidEndPoint(1308829567298285568l);
        int actualCode = response.extract().statusCode();
        Assert.assertEquals(404, actualCode);
    }
    @Test(enabled = true)
    public void testGetRetweets(){
        String tweet="We are learning RestAPI Automation and Tweet check";
        ValidatableResponse response=this.tweetAPIClient.getRetweets(1309358106808377344l);
        response.statusCode(200);
        String actualTweet=response.extract().body().path("text");
        Assert.assertEquals(tweet,actualTweet);
    }
    @Test(enabled = true)
    public void testFavoriteListTweet(){
        ValidatableResponse response=this.tweetAPIClient.favoriteListTweet("MobassaraT");
        int actualCode=response.extract().statusCode();
        System.out.println(actualCode);
        Assert.assertEquals(200, actualCode);
    }
    @Test(enabled = true)
    public void testFavoriteListTweetWithInvalidEndpoint(){
        ValidatableResponse response=this.tweetAPIClient.favoriteListTweetWithInvalidEndPoint("MobassaraT");
        int actualCode=response.extract().statusCode();
        System.out.println(actualCode);
        Assert.assertEquals(404, actualCode);
    }

    @Test(enabled = true)
    public void testGetHomeTimeLineTweets(){
        ValidatableResponse response=this.tweetAPIClient.getTimeLineTweet();
        int actualCode=response.extract().statusCode();
        System.out.println(actualCode);
        Assert.assertEquals(200,actualCode);
    }

    @Test(enabled = true)
    public void testGetTimeLineTweetWithInvalidEndPoint(){
        ValidatableResponse response=this.tweetAPIClient.getTimeLineTweetWithInvalidEndPoint();
        int actualCode = response.extract().statusCode();
        Assert.assertEquals(404, actualCode);
    }


    @Test(enabled = true)
    public void testCreateRetweetWithInvalidData(){
        ValidatableResponse response=this.tweetAPIClient.createReTweetWithInvalidData(1309188858433724422l);
        int actualReTweet=response.extract().statusCode();
        Assert.assertEquals(404,actualReTweet);
    }

    @Test(enabled = true)
    public void testUnReTweetInvalidId(){
        ValidatableResponse response=this.tweetAPIClient.unReTweetInvalidID(324236500424335363l);
        int actualUnRetweet=response.extract().statusCode();
        Assert.assertEquals(404,actualUnRetweet);
    }


}
