package com.tatyanayavkina.api.v1;

import com.tatyanayavkina.api.v1.dto.MakeVersionActiveRequest;
import com.tatyanayavkina.api.v1.dto.PublishVersionRequest;
import com.tatyanayavkina.exception.EntityNotFoundException;
import com.tatyanayavkina.model.App;
import com.tatyanayavkina.model.AppVersion;
import com.tatyanayavkina.model.ReleaseManager;
import com.tatyanayavkina.repository.AppRepository;
import com.tatyanayavkina.repository.AppVersionRepository;
import com.tatyanayavkina.repository.ReleaseManagerRepository;
import com.tatyanayavkina.internalvalidation.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Optional;

@RestController("/app-version")
public class AppVersionController {

    private final AppRepository appRepository;

    private final AppVersionRepository appVersionRepository;

    private final ReleaseManagerRepository releaseManagerRepository;

    private final ValidationService<AppVersion> validationService;

    @Autowired
    public AppVersionController(AppRepository appRepository, AppVersionRepository appVersionRepository,
                                ReleaseManagerRepository releaseManagerRepository,
                                ValidationService<AppVersion> validationService) {
        this.appRepository = appRepository;
        this.appVersionRepository = appVersionRepository;
        this.releaseManagerRepository = releaseManagerRepository;
        this.validationService = validationService;
    }

    @PostMapping(path = "/publish")
    @Transactional // todo: check for transaction or move logic to services
    public void publishVersion(@Valid @RequestBody PublishVersionRequest request) {
        App app = Optional.ofNullable(appRepository.findOne(request.getAppId()))
                .orElseThrow(() -> new EntityNotFoundException("App", request.getAppId()));
        ReleaseManager releaseManager = Optional.of(releaseManagerRepository.findOne(request.getReleaseManagerId()))
                .orElseThrow(() -> new EntityNotFoundException("ReleaseManager", request.getReleaseManagerId()));
        AppVersion appVersion = new AppVersion(app, request.getVersion(), releaseManager);
        appVersionRepository.save(appVersion);
    }

    @PostMapping(path = "/make-active")
    @Transactional
    public void makeVersionActive(@Valid @RequestBody MakeVersionActiveRequest request) {
        AppVersion appVersion = Optional.ofNullable(appVersionRepository.findOne(request.getAppVersionId()))
                .orElseThrow(() -> new EntityNotFoundException("AppVersion", request.getAppVersionId()));
        validationService.validate(appVersion);
        App app = appVersion.getApp();
        app.setActiveVersion(appVersion);
        appRepository.save(app);
    }
}
