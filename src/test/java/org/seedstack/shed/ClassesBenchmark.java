/**
 * Copyright (c) 2013-2016, The SeedStack authors <http://seedstack.org>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package org.seedstack.shed;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.seedstack.shed.reflect.Classes;

@State(Scope.Benchmark)
public class ClassesBenchmark {
    @Benchmark
    public void findHierarchyMethods() {
        Classes.from(SubClass.class)
                .traversingSuperclasses()
                .traversingInterfaces()
                .classes()
                .toArray();
    }

    @Benchmark
    public void findHierarchyFields() {
        Classes.from(SubClass.class)
                .traversingSuperclasses()
                .fields()
                .toArray();
    }

    @Benchmark
    public void findSpecificField() {
        Classes.from(SubClass.class)
                .traversingSuperclasses()
                .fields()
                .filter(field -> "baseClassField".equals(field.getName()))
                .findFirst();
    }

    interface SomeInterface {
        void someInterfaceMethod();
    }

    interface SomeOtherInterface {
        void someOtherInterfaceMethod();
    }

    static class BaseClass implements SomeInterface {
        private String baseClassField;

        public void someInterfaceMethod() {
        }
    }

    static class SubClass extends BaseClass implements SomeOtherInterface {
        private String subClassField;

        public void someInterfaceMethod() {
        }

        public void someOtherInterfaceMethod() {
        }
    }
}
