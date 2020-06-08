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

import java.awt.BorderLayout
import java.awt.GridBagLayout
import javax.swing.*
import javax.swing.tree.DefaultMutableTreeNode

class MainPanel : JPanel() {

  init {
    layout = BorderLayout()

    val treeRoot = DefaultMutableTreeNode("Project").apply {
      (1..100).map { "File $it.txt" }.forEach {
        add(DefaultMutableTreeNode(it))
      }
    }

    val tree = JTree(treeRoot)
    val scroll = JScrollPane(tree)

    add(scroll, BorderLayout.LINE_START)
    add(createTabbedPane(), BorderLayout.CENTER)
  }

  companion object {

    private fun createTable(): JComponent {
      val columnCount = 8
      val columnNames = (1..columnCount).map { "Column $it" }.toTypedArray()
      val rowCount = 200
      val data = Array(rowCount) { rowId ->
        Array(columnCount) { columnId -> "$rowId $columnId" }
      }
      return JScrollPane(JTable(data, columnNames))
    }

    private fun createComponentsPanel(): JPanel {
      val panel = JPanel(GridBagLayout())

      val variants = (1..10).map { "Variant $it" }.toTypedArray()
      val petList = JComboBox(variants)

      panel.add(petList)
      panel.add(JLabel("嗨"))
      panel.add(JTextField(20))

      return panel
    }

    private fun createTabbedPane(): JTabbedPane {
      val tabbedPane = JTabbedPane()
      val icon = UIManager.getIcon("FileView.directoryIcon")

      val tablePanel = createTable()
      tabbedPane.addTab("Table", icon, tablePanel, "Table Tip")

      val interactivePanel = createComponentsPanel()
      tabbedPane.addTab("Interactive", icon, interactivePanel, "Interactive Tip")

      customComponents.forEach {
        val name = it::class.simpleName
        tabbedPane.addTab("Tab for $name", icon, it, "Tab for $name Tip")
      }

      return tabbedPane
    }
  }
}
