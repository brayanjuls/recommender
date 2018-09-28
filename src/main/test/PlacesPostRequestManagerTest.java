import com.brayanjules.recommender.PlacesPost;
import com.brayanjules.recommender.Post;
import com.brayanjules.recommender.PostRequest;
import com.brayanjules.recommender.PostRequestManager;
import com.brayanjules.recommender.PostRequestStatusEnum;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by brayan on 05-09-18
 */

public class PlacesPostRequestManagerTest {
    private PostRequestManager postRequestManager;
    private Post post;
    private  PostRequest request;

    @Before
    public void setUp()
    throws JsonProcessingException {
        postRequestManager=new PostRequestManager();
        post=new PlacesPost("travesia tour to huilo huilo","travesia tour",3,new Date(),new Date(System.currentTimeMillis()+(86000*1000)),
                0.0, 1L,true,"");

        request=new PostRequest(new ObjectMapper().writeValueAsString(post),post.getUserId());
    }

    @Test
    public void createRequest()
    throws JsonProcessingException {
        assertNotNull(postRequestManager.createRequest(post));
    }

    @Test
    public void requestStatusTest() {
        assertEquals(PostRequestStatusEnum.WAITING,request.getStatus());

    }

    @Test
    public void acceptRequest(){
        postRequestManager.accept(request,new ArrayList<>(),new ArrayList<>());
        assertEquals(PostRequestStatusEnum.ACCEPTED,request.getStatus());
    }

    @Test
    public void acceptDeletedRequest(){
        request.setStatus(PostRequestStatusEnum.DELETED);
        postRequestManager.accept(request,new ArrayList<>(),new ArrayList<>());
        assertEquals(PostRequestStatusEnum.DELETED,request.getStatus());
    }

    @Test
    public void rejectRequest(){
        postRequestManager.reject(request);
        assertEquals(PostRequestStatusEnum.REJECTED,request.getStatus());
    }

    @Test
    public void deleteRequest(){
        postRequestManager.delete(request);
        assertEquals(PostRequestStatusEnum.DELETED,request.getStatus());
    }

    @Test
    public void validCreationDescriptionAndTitle(){

        assertEquals("travesia tour to huilo huilo",post.getDescription());
        assertEquals("travesia tour",post.getTitle());
    }

    @Test
    public void validateIdLength(){
        String[] parts = post.getId().split("\\|");
        assertEquals(4,parts.length);
    }

}
