package store;

import com.mrporter.pomangam.client.domains.store.Store;
import com.mrporter.pomangam.client.domains.store.category.StoreCategory;
import com.mrporter.pomangam.client.services.store.StoreServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.awt.print.Book;


@RunWith(SpringRunner.class)
@WebMvcTest(StoreServiceImpl.class)
public class StoreTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private StoreServiceImpl storeService;


    @Test
    public void test() throws Exception {

    }
}
