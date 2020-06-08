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
package org.jetbrains.projector.demo.ui.footer

import java.awt.Dimension
import javax.swing.BoxLayout
import javax.swing.JLabel
import javax.swing.JPanel
import javax.swing.SwingConstants
import javax.swing.border.BevelBorder

class FooterPanel : JPanel() {

  init {
    border = BevelBorder(BevelBorder.LOWERED)
    preferredSize = Dimension(0, 16)
    layout = BoxLayout(this, BoxLayout.X_AXIS)

    val statusLabel = JLabel("Status: ready").apply {
      horizontalAlignment = SwingConstants.LEFT
    }
    add(statusLabel)
  }
}
