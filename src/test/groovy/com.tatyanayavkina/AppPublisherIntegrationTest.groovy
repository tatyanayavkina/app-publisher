package com.tatyanayavkina

import com.tatyanayavkina.api.v1.dto.ErrorResponse
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals
import static org.junit.Assert.assertTrue

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=SpringBootTest.WebEnvironment.RANDOM_PORT)
class AppPublisherIntegrationTest {

    @Autowired
    TestRestTemplate restTemplate
    @Autowired
    DatabaseSetup databaseSetup;

    @Test
    void testPublishVersionSuccess() {
        def request = [
                appId: databaseSetup.result.appId,
                version: "version-for-publish",
                releaseManagerId: databaseSetup.result.activeReleaseManagerId
        ]

        ResponseEntity<Void> responseEntity = restTemplate.postForEntity("/api/v1/app-version/publish",
                request,
                Void.class)

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode())
    }

    @Test
    void testPublishVersionWithInvalidAppId() {
        def request = [
                appId: 1250,
                version: "version-for-publish",
                releaseManagerId: databaseSetup.result.activeReleaseManagerId
        ]

        ResponseEntity<ErrorResponse> responseEntity = restTemplate.postForEntity("/api/v1/app-version/publish",
                request,
                ErrorResponse.class)
        String message = responseEntity.getBody().getMessage()

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode())
        assertTrue(message.contains("entity with id=1250 not found"))
    }

    @Test
    void testMakeVersionActiveSuccess() {
        ResponseEntity<Void> responseEntity =
                restTemplate.postForEntity("/api/v1/app-version/make-active",
                        [appVersionId: databaseSetup.result.appVersionIdWithActiveReleaseManager],
                        Void.class)

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode())
    }

    @Test
    void testMakeVersionActiveValidationError() {
        ResponseEntity<ErrorResponse> responseEntity =
                restTemplate.postForEntity("/api/v1/app-version/make-active",
                        [appVersionId: databaseSetup.result.appVersionIdWithInactiveReleaseManagerId],
                        ErrorResponse.class)
        String message = responseEntity.getBody().getMessage()

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode())
        assertTrue(message.contains("Model is not valid"))
    }
}
