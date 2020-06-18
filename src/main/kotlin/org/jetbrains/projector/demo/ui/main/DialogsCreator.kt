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
package org.jetbrains.projector.demo.ui.main

import java.awt.Dimension
import java.awt.FlowLayout
import javax.swing.*

class DialogsCreator : JPanel() {

  init {
    layout = BoxLayout(this, BoxLayout.Y_AXIS)

    JButton("Frame").let {
      it.addActionListener {
        val window = SwingUtilities.windowForComponent(this)

        JFrame("Frame").apply {
          size = Dimension(400, 240)
          layout = FlowLayout()
          add(JLabel("You can enter something:"))
          add(JTextField(15))
          add(JButton("You can press this submit button"))

          JButton("Another Frame").let { anotherFrameButton ->
            anotherFrameButton.addActionListener {
              val anotherWindow = SwingUtilities.windowForComponent(anotherFrameButton)

              JFrame("Another Frame").apply {
                size = Dimension(240, 400)
                layout = FlowLayout()
                add(JLabel("You can enter something:"))
                add(JTextField(15))
                add(JButton("You can press this submit button"))

                setLocationRelativeTo(anotherWindow)
                isVisible = true
              }
            }

            add(anotherFrameButton)
          }

          setLocationRelativeTo(window)
          isVisible = true
        }
      }

      add(it)
    }

    JButton("Popup").let { popupButton ->
      popupButton.addActionListener {
        val buttonPoint = popupButton.locationOnScreen
        JPanel().apply {
          border = BorderFactory.createLoweredBevelBorder()

          val closeButton = JButton("Close")
          add(closeButton)
          add(JLabel("Text in popup content"))
          val popup = PopupFactory.getSharedInstance().getPopup(popupButton, this, buttonPoint.x - 50, buttonPoint.y - 5).also {
            it.show()
          }

          closeButton.addActionListener {
            popup.hide()
          }
        }
      }

      add(popupButton)
    }

    JButton("Confirm Dialog").let {
      it.addActionListener {
        val window = SwingUtilities.windowForComponent(this)
        JOptionPane.showConfirmDialog(window, "Yes or no?", "Confirm Dialog", JOptionPane.YES_NO_OPTION)
      }

      add(it)
    }
  }
}
