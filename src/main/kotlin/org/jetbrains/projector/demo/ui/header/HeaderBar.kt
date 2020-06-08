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
package org.jetbrains.projector.demo.ui.header

import javax.swing.JMenu
import javax.swing.JMenuBar
import javax.swing.JMenuItem

class HeaderBar : JMenuBar() {

  init {
    listOf("File", "Edit", "View", "Navigate", "Code", "Analyze", "Refactor", "Build").forEach { name ->
      val m1 = JMenu(name)

      val m11 = JMenu("New").apply {
        add(JMenuItem("Project"))
        add(JMenuItem("Project from existing sources..."))
        add(JMenuItem("Project from version control"))
      }

      val m22 = JMenu("Open").apply {
        add(JMenuItem("Open recent"))
      }

      m1.add(m11)
      m1.add(m22)
      add(m1)
    }
  }
}
