package org.example.spu;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpuApplicationTests {

	@Test
	void contextLoads() {
	}

//	@Test
//	public void givenBidirectionRelation_whenUsingJacksonReferenceAnnotationWithSerialization_thenCorrect() throws JsonProcessingException {
//		final User user = new User(1, "John");
//		final Item item = new Item(2, "book", user);
//		user.addItem(item);
//
//		final String itemJson = new ObjectMapper().writeValueAsString(item);
//		final String userJson = new ObjectMapper().writeValueAsString(user);
//
//		assertThat(itemJson, containsString("book"));
//		assertThat(itemJson, not(containsString("John")));
//
//		assertThat(userJson, containsString("John"));
//		assertThat(userJson, containsString("userItems"));
//		assertThat(userJson, containsString("book"));
//	}

}
