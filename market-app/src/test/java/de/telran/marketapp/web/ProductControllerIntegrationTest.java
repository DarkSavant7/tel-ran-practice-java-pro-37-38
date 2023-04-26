package de.telran.marketapp.web;

import de.telran.marketapp.client.FakeExternalServiceClient;
import de.telran.marketapp.entities.Product;
import de.telran.marketapp.mapper.ProductMapper;
import de.telran.marketapp.mapper.ProductMapstructMapper;
import de.telran.marketapp.repositiory.ProductRepository;
import de.telran.marketapp.services.ProductService;
import de.telran.marketapp.util.FakeHolder;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.client.RestTemplate;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.UUID;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@FieldDefaults(level = AccessLevel.PRIVATE)
@Testcontainers
@ActiveProfiles("test")
@AutoConfigureMockMvc
class ProductControllerIntegrationTest {

    @Autowired
    ProductController productController;
    @Autowired
    ProductService service;

//    @MockBean
    @Autowired
    ProductRepository repository;
    @Autowired
    ProductMapper productMapper;
    @Autowired
    ProductMapstructMapper productMapstructMapper;
    @Autowired
    MockMvc mockMvc;
    @Autowired
    FakeExternalServiceClient fakeExternalServiceClient;
    @Autowired
    RestTemplate restTemplate;
//    @MockBean
//    @SpyBean

    String url = "/products";

    @Container
    private static MySQLContainer container = new MySQLContainer("mysql")
            .withDatabaseName("market")
            .withUsername("admin")
            .withPassword("admin");

    @DynamicPropertySource
    static void properties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", container::getJdbcUrl);
        registry.add("spring.datasource.password", container::getPassword);
        registry.add("spring.datasource.username", container::getUsername);
    }

    @Test
    @WithMockUser(username = "TEST_VASYA", roles = {"TESTER"})
//    @WithMockUser(username = "TEST_VASYA", roles = {"TESTER"}, authorities = {"AUTHORITY_READ"})
    void create() throws Exception {
//        var prod = Product.builder()
//                .name("test-prod")
//                .description("test-description")
//                .price(BigDecimal.TEN)
//                .tags(Collections.emptySet())
//                .id(UUID.randomUUID())
//                .build();
//        doReturn(prod).when(repository).save(any());
//        when(repository.save(any())).thenReturn(prod);
//      var result =  productController.create();
        var builder = MockMvcRequestBuilders.post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(FakeHolder.CREATE_PRODUCT_REQUEST);

        mockMvc.perform(builder)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("test-prod")))
//                .andExpect(jsonPath("$.collection[0].user.id", is("test-prod")))
//                .andExpect(jsonPath("$.collection", hasItem("test-prod")))
                .andExpect(jsonPath("$.description", is("test-description")));
//        verify(repository, times(1)).save(any());
    }

    @Test
    void findById() {
    }

    @Test
    void findAll() {
    }

    @Test
    void deleteById() {
    }

    @Test
    void testFindById() {
    }
}