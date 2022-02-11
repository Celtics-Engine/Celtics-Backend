
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.celticsengine.assetstore.dynamodb.CelticUsersDao;
import com.celticsengine.assetstore.dynamodb.models.CelticUser;
import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.UUID;

import static io.jsonwebtoken.lang.Assert.*;
import static org.mockito.MockitoAnnotations.openMocks;

public class DynamodbTest {

    @Mock
    private CelticUsersDao dao;

    @Mock
    CelticUser user = new CelticUser();

    @BeforeEach
    void setup() {
        openMocks(this);
        dao = new CelticUsersDao(AmazonDynamoDBClientBuilder.defaultClient());
    }

    @Test
    void userSave_andLoad() {

        user.setUserId(UUID.randomUUID().toString());

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
