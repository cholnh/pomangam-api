package com.mrporter.pomangam._bases.utils.reflection;

import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface PatchIgnore {
}