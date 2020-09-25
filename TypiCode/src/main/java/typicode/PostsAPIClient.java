package typicode;

import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

public class PostsAPIClient extends TypiCodeAPIClient {

    private String POST_ENDPOINT = "/posts";
    private String DELETE_ENDPOINT = "/posts/111";

    public ValidatableResponse getAllPosts() {
        return when().get(this.baseUrl + POST_ENDPOINT).then();
    }

    public ValidatableResponse deleteAPost() {
        return when().delete(this.baseUrl + DELETE_ENDPOINT).then();
    }


    public ValidatableResponse createPost(Object json) {
        return given().header("Content-type", "application/json").body(json)
                .when().post(this.baseUrl + POST_ENDPOINT).then();
    }

    public ValidatableResponse createPost1(Object json) {
        return given().header("Content-type", "application/json; charset=UTF-8").body(json)
                .when().post(this.baseUrl + POST_ENDPOINT).then();
    }

    public ValidatableResponse deletePost(Object json) {
        return given().header("Content-type", "application/json; charset=UTF-8").body(json)
                .when().delete(this.baseUrl + POST_ENDPOINT).then();
    }



}
