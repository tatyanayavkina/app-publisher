package com.tatyanayavkina.api.v1;

import com.tatyanayavkina.api.v1.dto.MakeVersionActiveRequest;
import com.tatyanayavkina.api.v1.dto.PublishVersionRequest;
import com.tatyanayavkina.model.App;
import com.tatyanayavkina.model.AppVersion;
import com.tatyanayavkina.model.ReleaseManager;
import com.tatyanayavkina.repository.AppRepository;
import com.tatyanayavkina.repository.AppVersionRepository;
import com.tatyanayavkina.repository.ReleaseManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.Optional;

@RestController("/app-version")
public class AppVersionController {

    private final AppRepository appRepository;

    private final AppVersionRepository appVersionRepository;

    private final ReleaseManagerRepository releaseManagerRepository;

    @Autowired
    public AppVersionController(AppRepository appRepository, AppVersionRepository appVersionRepository,
                                ReleaseManagerRepository releaseManagerRepository) {
        this.appRepository = appRepository;
        this.appVersionRepository = appVersionRepository;
        this.releaseManagerRepository = releaseManagerRepository;
    }

    @PostMapping(path = "/publish")
    @Transactional
    public void publishVersion(@Valid @RequestBody PublishVersionRequest request) {
        App app = Optional.ofNullable(appRepository.findOne(request.getAppId()))
                .orElseThrow(() -> new EntityNotFoundException()); //todo: use exception accepting class and id, add exception handler
        ReleaseManager releaseManager = Optional.of(releaseManagerRepository.findOne(request.getReleaseManagerId()))
                .orElseThrow(() -> new EntityNotFoundException()); // or add validation for existence
        AppVersion appVersion = new AppVersion(app, request.getVersion(), releaseManager);
        appVersionRepository.save(appVersion);
    }

    @PostMapping(path = "/make-active")
    public void makeVersionActive(@Valid @RequestBody MakeVersionActiveRequest request) {

    }
}
