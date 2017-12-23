package com.tatyanayavkina

import com.tatyanayavkina.model.App
import com.tatyanayavkina.model.AppValidationRule
import com.tatyanayavkina.model.AppVersion
import com.tatyanayavkina.model.Publisher
import com.tatyanayavkina.model.ReleaseManager
import com.tatyanayavkina.model.ValidationType
import com.tatyanayavkina.repository.AppRepository
import com.tatyanayavkina.repository.AppValidationRuleRepository
import com.tatyanayavkina.repository.AppVersionRepository
import com.tatyanayavkina.repository.PublisherRepository
import com.tatyanayavkina.repository.ReleaseManagerRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

import javax.annotation.PostConstruct

@Component
class DatabaseSetup {

    @Autowired
    AppRepository appRepository

    @Autowired
    AppValidationRuleRepository appValidationRuleRepository

    @Autowired
    AppVersionRepository appVersionRepository

    @Autowired
    PublisherRepository publisherRepository

    @Autowired
    ReleaseManagerRepository releaseManagerRepository

    DatabaseSetupResult result = new DatabaseSetupResult()

    @PostConstruct
    public void warm() {
        //set up validation rules for app version
        //1
        AppValidationRule ruleMinLength = new AppValidationRule()
        ruleMinLength.setType(ValidationType.MIN_LENGTH)
        ruleMinLength.setPath("version")
        appValidationRuleRepository.save(ruleMinLength)
        //2
        AppValidationRule ruleMaxLength = new AppValidationRule()
        ruleMaxLength.setType(ValidationType.MAX_LENGTH)
        ruleMaxLength.setPath("version")
        appValidationRuleRepository.save(ruleMaxLength)
        //3
        AppValidationRule ruleIsTrue = new AppValidationRule()
        ruleIsTrue.setType(ValidationType.IS_TRUE)
        ruleIsTrue.setPath("releaseManager.active")
        appValidationRuleRepository.save(ruleIsTrue)

        // create publisher
        Publisher publisher = new Publisher()
        publisher.setName("publisher-1")
        publisher = publisherRepository.save(publisher)
        result.publisherId = publisher.getId()

        // create app
        App app = new App()
        app.setName("APP-1")
        app.setPublisher(publisher)
        app = appRepository.save(app)
        result.appId = app.getId()

        //create active and inactive release managers
        ReleaseManager activeManager = new ReleaseManager()
        activeManager.setName("Active manager")
        activeManager.setActive(true)
        activeManager = releaseManagerRepository.save(activeManager)
        result.activeReleaseManagerId = activeManager.getId()

        ReleaseManager inactiveManager = new ReleaseManager()
        inactiveManager.setName("Inactive manager")
        inactiveManager.setActive(false)
        releaseManagerRepository.save(inactiveManager)

        //create app version with active manager
        AppVersion appVersionWithActiveManager = new AppVersion()
        appVersionWithActiveManager.setApp(app)
        appVersionWithActiveManager.setReleaseManager(activeManager)
        appVersionWithActiveManager.setVersion("version-15")
        appVersionWithActiveManager = appVersionRepository.save(appVersionWithActiveManager)
        result.appVersionIdWithActiveReleaseManager = appVersionWithActiveManager.getId()

        //create app version with inactive manager
        AppVersion appVersionWithInactiveManager = new AppVersion()
        appVersionWithInactiveManager.setApp(app)
        appVersionWithInactiveManager.setReleaseManager(inactiveManager)
        appVersionWithInactiveManager.setVersion("version-gg")
        appVersionWithInactiveManager = appVersionRepository.save(appVersionWithInactiveManager)
        result.appVersionIdWithInactiveReleaseManagerId = appVersionWithInactiveManager.getId()
    }
}