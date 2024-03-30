package com.ms.notion.services.impl;

import com.ms.notion.dtos.integrations.NotionIntegrationDTO;
import com.ms.notion.dtos.integrations.NotionParametersIntegrationDTO;
import com.ms.notion.repositories.NotionIntegrationRepository;
import com.ms.notion.services.impl.notion.NotionIntegrationServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.net.URISyntaxException;
import static org.junit.jupiter.api.Assertions.assertEquals;
class NotionIntegrationServiceImplTest {

    @Mock
    private NotionIntegrationRepository repository;

    @InjectMocks
    private NotionIntegrationServiceImpl notionIntegrationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void createNotionIntegrationModel() throws URISyntaxException {
        var notionIntegrationDTO = new NotionIntegrationDTO("Personal Assistant",
                "https://www.notion.so/",
                new NotionParametersIntegrationDTO("secret_VxyOZPpQQaPAkCuXIoVv2VXNMVMvc6Y5SWy3asFbizb", "Notion-Version: 2022-06-28"));
        var response = notionIntegrationService.createNotionIntegrationModel(notionIntegrationDTO);
        assertEquals(201, response.getCode());
    }

    @Test
    void findNotionIntegrationById() {
    }

    @Test
    void testCreateNotionIntegrationModel() {
    }

    @Test
    void testFindNotionIntegrationById() {
    }
}