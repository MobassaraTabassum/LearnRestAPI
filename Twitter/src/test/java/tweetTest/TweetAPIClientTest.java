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
        String tweet="I am practising RestAPI Automation"+ UUID.randomUUID().toString();
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
    @Test(enabled = false)
    public void testDelete(){
        String tweet="We are learning RestAPI Automation and Tweet check";
        ValidatableResponse response=this.tweetAPIClient.deleteTweet(1305277715365404674l);
        // Verify that the tweet was successfully deleted
        response.statusCode(200);
        String actualTweet=response.extract().body().path("text");
        Assert.assertEquals(tweet,actualTweet);
    }
    @Test(enabled = false)
    public void testReTweet(){
        String tweet="RT @BarackObama: This Labor Day, let’s thank all those who've kept our country going this year—nurses, teachers, delivery drivers, food service workers, and many more. We can honor them by building our system back even better—so that essential workers are treated like it, pandemic or not.";
        ValidatableResponse response=this.tweetAPIClient.reTweet(1303015313320050688l);
        // Verify that the tweet was successfully deleted
        response.statusCode(200);
        String actualTweet=response.extract().body().path("text");
        Assert.assertEquals(tweet,actualTweet);
    }


 @Test(enabled = false)
    public void testUnReTweet(){
        String tweet="This Labor Day, let’s thank all those who've kept our country going this year—nurses, " +
                "teachers, delivery drivers, food service workers, and many more. We can honor them by building our system back even better—so that essential workers are treated like it, pandemic or not.";
        ValidatableResponse response=this.tweetAPIClient.unReTweet(1303015313320050688l);
        // Verify that the tweet was successfully deleted
        response.statusCode(200);
        String actualTweet=response.extract().body().path("text");
        Assert.assertEquals(tweet,actualTweet);
    }


 @Test(enabled = true)
    public void testShowTweetID(){
        String tweet="hello";
        ValidatableResponse response=this.tweetAPIClient.showTweetID(13760868246l);
        // Verify that the tweet was successfully deleted
        response.statusCode(200);
        String actualTweet=response.extract().body().path("text");
        Assert.assertEquals(tweet,actualTweet);
    }


    @Test(enabled = true)
    public void FavoritesTweetID(){
        String tweet="hey there";
//        ValidatableResponse response=this.tweetAPIClient.favoritesTweet(1308874571995664386L);
        ValidatableResponse response=this.tweetAPIClient.favoritesTweet(1307111166636969988L);
        response.statusCode(200);
        String actualTweet=response.extract().body().path("text");
        Assert.assertEquals(tweet,actualTweet);
    }




}
