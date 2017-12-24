package com.tatyanayavkina;

import com.tatyanayavkina.exception.ObjectNotValidException;
import com.tatyanayavkina.internalvalidation.processor.IsTrueValidationProcessor;
import com.tatyanayavkina.internalvalidation.processor.MaxLengthValidationProcessor;
import com.tatyanayavkina.internalvalidation.processor.MinLengthValidationProcessor;
import com.tatyanayavkina.internalvalidation.processor.ValidationProcessor;
import com.tatyanayavkina.model.App;
import com.tatyanayavkina.model.AppVersion;
import com.tatyanayavkina.model.Publisher;
import com.tatyanayavkina.model.ReleaseManager;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class ValidationProcessorTest {

    private final static String PATH_TO_MANAGER_ACTIVE = "releaseManager.active";

    private final ValidationProcessor isTrueValidationProcessor = new IsTrueValidationProcessor();

    private final ValidationProcessor maxLengthValidationProcessor = new MaxLengthValidationProcessor();

    private final ValidationProcessor minLengthValidationProcessor = new MinLengthValidationProcessor();
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void testPropertyPathIsWrong() {
        AppVersion model = new AppVersion(new App(), "version", null);

        expectedException.expect(IllegalArgumentException.class);

        isTrueValidationProcessor.validate(model, PATH_TO_MANAGER_ACTIVE);
    }

    @Test
    public void testPropertyIsNull() {
        AppVersion model = new AppVersion(new App(), "version", null);

        expectedException.expect(ClassCastException.class);

        maxLengthValidationProcessor.validate(model, "releaseManager");
    }

    @Test
    public void testPropertyIsUnsupportedType() {
        AppVersion model = new AppVersion(new App(), "version", null);

        expectedException.expect(ClassCastException.class);

        isTrueValidationProcessor.validate(model, "version");
    }

    @Test
    public void testObjectIsNotValidForIsTrueValidationProcessor() {
        ReleaseManager manager = new ReleaseManager();
        manager.setActive(false);
        AppVersion model = new AppVersion(new App(), "version", manager);

        expectedException.expect(ObjectNotValidException.class);

        isTrueValidationProcessor.validate(model, PATH_TO_MANAGER_ACTIVE);
    }

    @Test
    public void testHappyPathForIsTrueValidationProcessor() {
        ReleaseManager manager = new ReleaseManager();
        manager.setActive(true);
        AppVersion model = new AppVersion(new App(), "version", manager);

        isTrueValidationProcessor.validate(model, PATH_TO_MANAGER_ACTIVE);
    }

    @Test
    public void testObjectIsNotValidForMinLengthValidationProcessor() {
        Publisher model = new Publisher();
        model.setName("abc");

        expectedException.expect(ObjectNotValidException.class);

        minLengthValidationProcessor.validate(model, "name");
    }

    @Test
    public void testHappyPathForIsMinLengthValidationProcessor() {
        Publisher model = new Publisher();
        model.setName("Longer-Than-5");

        minLengthValidationProcessor.validate(model, "name");
    }

    @Test
    public void testObjectIsNotValidForMaxLengthValidationProcessor() {
        Publisher model = new Publisher();
        model.setName("Longer-Than-15-symbols-And-It-Is-Bad");

        expectedException.expect(ObjectNotValidException.class);

        maxLengthValidationProcessor.validate(model, "name");
    }

    @Test
    public void testHappyPathForIsMaxLengthValidationProcessor() {
        Publisher model = new Publisher();
        model.setName("Shorter-Than-15");

        maxLengthValidationProcessor.validate(model, "name");
    }
}
