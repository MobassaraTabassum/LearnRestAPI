package tweet;

import base.RestAPI;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class TweetAPIClient extends RestAPI {

    private final String CREATE_TWEET_ENDPOINT = "/statuses/update.json";
    private final String DELETE_TWEET_ENDPOINT = "/statuses/destroy.json";
    private final String GET_USER_TWEET_ENDPOINT = "/statuses/user_timeline.json";
    private final String RETWEET_POST_USER_ENDPOINT = "/statuses/retweet.json";
    private final String UNRETWEET_POST_USER_ENDPOINT = "/statuses/unretweet.json";
    private final String SHOW_GET_USER_ENDPOINT = "/statuses/show.json?id=13760868246";
    private final String FAVORITES_POST_USER_ENDPOINT = "/favorites/create.json";

    // https://api.twitter.com/1.1/statuses/update.json
    //
    //https://developer.twitter.com/en/docs/twitter-api/v1/tweets/timelines/api-reference/get-statuses-user_timeline
    private final String RETWEET_USER_TWEET_ENDPOINT = "/statuses/retweet.json";
    private final String READ_GET_USER_ENDPOINT = "/statuses/show.json";
    private final String STATUS_LOOKUP_GET_USER_ENDPOINT = "/statuses/lookup.json";
    private final String GET_RETWEETS_USER_ENDPOINT = "/statuses/retweets.json";
    private final String CREATE_RETWEET_ENDPOINT = "/statuses/retweet.json";
    private final String FAVORITES_DESTROY_POST_USER_ENDPOINT = "/favorites/destroy.json";
    private final String FAVORITES_LIST_USER_ENDPOINT="/favorites/list.json";
    private final String GET_HOME_TIMELINE_USER_ENDPOINT="/statuses/home_timeline.json";

    // GET ALL Tweet Information
    public ValidatableResponse getUserTimeTweet() {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .when().get(this.baseUrl + this.GET_USER_TWEET_ENDPOINT)
                .then();
    }

    // Create a Tweet from user twitter
    public ValidatableResponse createTweet(String tweet) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .param("status", tweet)
                .when().post(this.baseUrl + this.CREATE_TWEET_ENDPOINT)
                .then();
    }

    // Delete a tweet from users twitter
    public ValidatableResponse deleteTweet(Long tweetId) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .queryParam("id", tweetId)
                .when().post(this.baseUrl + this.DELETE_TWEET_ENDPOINT)
                .then();
    }

    public ValidatableResponse reTweet(Long tweetId) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .queryParam("id", tweetId)
                .when().post(this.baseUrl + this.RETWEET_POST_USER_ENDPOINT)
                .then();
    }
    public ValidatableResponse unReTweet(Long tweetId) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .queryParam("id", tweetId)
                .when().post(this.baseUrl + this.UNRETWEET_POST_USER_ENDPOINT)
                .then();
    }

    public ValidatableResponse unReTweetInvalidID(Long tweetId) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .queryParam("id", tweetId)
                .when().post(this.baseUrl + this.UNRETWEET_POST_USER_ENDPOINT)
                .then();
    }




    public ValidatableResponse showTweetID(Long tweetId) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .queryParam("id", tweetId)
                .when().get(this.baseUrl + this.SHOW_GET_USER_ENDPOINT)
                .then();
    }

    /**
     * Show tweet with invalid data
     */
    public ValidatableResponse showTweetIDWithInvalidData(Long tweetId) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .queryParam("id", tweetId)
                .when().get(this.baseUrl + this.SHOW_GET_USER_ENDPOINT)
                .then();
    }

    public ValidatableResponse favoritesTweet(Long tweetId) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .param("id", tweetId)
                .when().post(this.baseUrl + this.FAVORITES_POST_USER_ENDPOINT)
                .then();
    }
    //group

    public ValidatableResponse createTweetWithWrongFavoritesInvalidEndPoint(long tweetId) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .param("id", tweetId)
                .when().post(this.baseUrl + this.FAVORITES_POST_USER_ENDPOINT + "1234")
                .then();
    }

    /**
     *  GET ALL Tweet Information
     * @return
     */
    public ValidatableResponse getUserTimeTweet(long tweetId) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .param("id", tweetId)
                .when().get(this.baseUrl + this.GET_USER_TWEET_ENDPOINT)
                .then();
    }



    /**
     * Create ReTweet
     * @param tweetId
     * @return
     */
    public ValidatableResponse createRetweet(Long tweetId) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .param("id", tweetId)
                .when().post(this.baseUrl + this.RETWEET_USER_TWEET_ENDPOINT)
                .then();
    }




    /**
     * Create Read Tweet
     * @param tweetId
     * @return
     */
    public ValidatableResponse readTweet(Long tweetId) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .param("id", tweetId)
                .when().get(this.baseUrl + this.READ_GET_USER_ENDPOINT)
                .then();
    }

    /**
     * create Status LokUp
     * @param tweetId
     * @return
     */
    public ValidatableResponse getStatusLookUp(long tweetId) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .queryParam("id", tweetId)
                .when().get(this.baseUrl + this.STATUS_LOOKUP_GET_USER_ENDPOINT)
                .then();
    }

    /**
     * create Status LookUp with valid data
     */
    public ValidatableResponse getStatusLookUp(int id,String tweetId) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .queryParam("id",id, tweetId)
                .when().get(this.baseUrl + this.STATUS_LOOKUP_GET_USER_ENDPOINT)
                .then();
    }
    /**
     * create Status LookUp with invalid data
     */
    public ValidatableResponse getStatusLookUpWithInvalidData(int id,String tweetId) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .queryParam("id",id, tweetId)
                .when().get(this.baseUrl + this.STATUS_LOOKUP_GET_USER_ENDPOINT+"1234")
                .then();
    }
    /**
     * Create Favorites Tweet
     * @param tweetId
     * @return
     */
    public ValidatableResponse createFavoritesTweet(Long tweetId) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .param("id", tweetId)
                .when().post(this.baseUrl + this.FAVORITES_POST_USER_ENDPOINT)
                .then();
    }

    /**
     * Create Unlike favorites tweet
     */

    public ValidatableResponse unlikeFavoritesTweet(Long tweetId) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .queryParam("id", tweetId)
                .when().post(this.baseUrl + this.FAVORITES_DESTROY_POST_USER_ENDPOINT)
                .then();
    }

    /**
     * Get Status Retweets
     * @param tweetId
     * @return
     */
    public ValidatableResponse getRetweets(long tweetId){
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .queryParam("id", tweetId)
                .when().get(this.baseUrl + this.GET_RETWEETS_USER_ENDPOINT )
                .then();
    }
    /**
     *Favorite List
     * @param userId
     * @return
     */
    public ValidatableResponse favoriteListTweet(String userId) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .param("user_id", userId)
                .when().get(this.baseUrl + this.FAVORITES_LIST_USER_ENDPOINT)
                .then();
    }
    public ValidatableResponse favoriteListTweetWithInvalidEndPoint(String userId) {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .param("user_id", userId)
                .when().get(this.baseUrl + this.FAVORITES_LIST_USER_ENDPOINT+1)
                .then();
    }
    /**
     *Get time line tweet
     */
    public ValidatableResponse getTimeLineTweet() {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .when().get(this.baseUrl + this.GET_HOME_TIMELINE_USER_ENDPOINT )
                .then();
    }
    public ValidatableResponse getTimeLineTweetWithInvalidEndPoint() {
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .when().get(this.baseUrl + this.GET_HOME_TIMELINE_USER_ENDPOINT+1 )
                .then();
    }

    /**
     * Create reTweet with invalid data
     */
    public ValidatableResponse createReTweetWithInvalidData(Long reTweetId){
        return given().auth().oauth(this.apiKey, this.apiSecretKey, this.accessToken, this.accessTokenSecret)
                .param("id", reTweetId)
                .when().post(this.baseUrl+this.CREATE_RETWEET_ENDPOINT+"1234")
                .then();
    }






}
