
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.celticsengine.assetstore.dynamodb.CelticUsersDao;
import com.celticsengine.assetstore.dynamodb.models.CelticUsers;
import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;

import static io.jsonwebtoken.lang.Assert.*;


public class DynamodbTest {

    private CelticUsersDao dao;
    CelticUsers user = new CelticUsers();

    @BeforeEach
    void setup() {
         dao = new CelticUsersDao(AmazonDynamoDBClientBuilder.defaultClient());
    }

    @Test
    void userSave_andLoad() {

        dao.saveCelticUsers(user);
        dao.load(user);

        notNull(user.getUsername());
        notNull(user.getUserId());
        notNull(user.getDateCreated());
        notNull(user.getPassword());
    }

    @Test
    void testGetUser() {

    }

    @Test
    void testUpdateUser() {

    }

    @Test
    void testDeleteUser() {

    }

}
