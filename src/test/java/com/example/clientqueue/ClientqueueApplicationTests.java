package com.example.clientqueue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.Optional;

@SpringBootTest
class ClientqueueApplicationTests {

	@Autowired
	private ClientQueue clientQueue;

	@Test
	public void insertNewClient() {
		Long id = clientQueue.insertNewClient("John Doe", false);
		Assert.notNull(id, "Expected id to be not null");
	}

	@Test
	public void serveClientWithoutPriority() {
		Long id = clientQueue.insertNewClient("John Doe", false);
		Optional<Client> clientOptional = clientQueue.serveClient();
		Assert.isTrue(clientOptional.isPresent(), "Expected client ot exist");
		Assert.isTrue(clientOptional.get().getId().equals(id), "Client ids should be the same");
	}

	@Test
	public void serveClientWithPriority() {
		Long id1 = clientQueue.insertNewClient("John Doe", false);
		Long id2 = clientQueue.insertNewClient("John Smith", true);
		Optional<Client> clientOptional = clientQueue.serveClient();
		Assert.isTrue(clientOptional.isPresent(), "Expected client ot exist");
		Assert.isTrue(clientOptional.get().getId().equals(id2), "Should get the client with priority");
	}
}
