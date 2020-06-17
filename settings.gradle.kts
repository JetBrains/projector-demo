/*
 * GNU General Public License version 2
 *
 * Copyright (C) 2019-2020 JetBrains s.r.o.
 *
 * This program is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along
 * with this program; if not, write to the Free Software Foundation, Inc.,
 * 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 */
pluginManagement {
  val kotlinVersion: String by settings

  plugins {
    kotlin("jvm") version kotlinVersion apply false
  }
}

rootProject.name = "projector-demo"

val localProperties = java.util.Properties().apply {
  try {
    load(File(rootDir, "local.properties").inputStream())
  }
  catch (t: Throwable) {
    println("Can't read local.properties: $t, assuming empty")
  }
}

if (localProperties["useLocalProjectorServer"] == "true") {
  includeBuild("../projector-server") {
    dependencySubstitution {
      substitute(module("com.github.JetBrains.projector-server:projector-server")).with(project(":projector-server"))
    }
  }
}
