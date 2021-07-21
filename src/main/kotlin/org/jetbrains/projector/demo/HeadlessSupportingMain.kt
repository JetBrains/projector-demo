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
package org.jetbrains.projector.demo

import org.jetbrains.projector.demo.ui.createFrame
import org.jetbrains.projector.server.ProjectorLauncher
import org.jetbrains.projector.server.ProjectorServer
import javax.swing.UIManager

object HeadlessSupportingMain {

  @JvmStatic
  fun main(args: Array<String>) {
    // This is how you can change your UI application to support running in the headless mode

    // ProjectorServer has its own method to check if it's enabled (via System Properties).
    // You can use this way or your own one.

    if (ProjectorServer.isEnabled) {
      val success = ProjectorLauncher.runProjectorServer()
      check(success)
    }

    // Perform AWT operations such as creating a frame only after launching the HeadlessServer.
    // If you do that before and do that under headless JVM, there can be a crash.

    UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName())

    createFrame().apply {
      isVisible = true
    }
  }
}
