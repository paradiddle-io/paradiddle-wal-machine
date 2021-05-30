/*
 * paradiddle-wal-machine A generic state machine library that utilizes a write-ahead log for consistency.
 * Copyright (c) 2021 Michael Juliano
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along
 * with this program; if not, write to:
 *
 *     Free Software Foundation, Inc.,
 *     51 Franklin Street, Fifth Floor,
 *     Boston, MA 02110-1301 USA.
 */

plugins {
    groovy
    `java-library`
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.codehaus.groovy:groovy:3.0.8")
    testImplementation("org.spockframework:spock-core:2.0-M4-groovy-3.0")
    testImplementation("junit:junit:4.13.2")
}

tasks.test {
    useJUnitPlatform()
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(16))
    }
}
