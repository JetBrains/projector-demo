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
package org.jetbrains.projector.demo.ui

import org.jetbrains.projector.demo.ui.footer.FooterPanel
import org.jetbrains.projector.demo.ui.header.HeaderBar
import org.jetbrains.projector.demo.ui.main.MainPanel
import java.awt.BorderLayout
import javax.swing.JFrame

fun createFrame() = JFrame("Test UI").apply {
  defaultCloseOperation = JFrame.EXIT_ON_CLOSE
  setSize(1280, 720)

  contentPane.apply {
    layout = BorderLayout()
    add(HeaderBar(), BorderLayout.PAGE_START)
    add(MainPanel(), BorderLayout.CENTER)
    add(FooterPanel(), BorderLayout.PAGE_END)
  }
}
