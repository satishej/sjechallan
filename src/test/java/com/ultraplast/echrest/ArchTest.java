package com.ultraplast.echrest;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {
        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("com.ultraplast.echrest");

        noClasses()
            .that()
            .resideInAnyPackage("com.ultraplast.echrest.service..")
            .or()
            .resideInAnyPackage("com.ultraplast.echrest.repository..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..com.ultraplast.echrest.web..")
            .because("Services and repositories should not depend on web layer")
            .check(importedClasses);
    }
}
