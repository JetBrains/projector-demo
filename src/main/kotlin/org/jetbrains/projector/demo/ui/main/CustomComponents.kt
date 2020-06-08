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

import java.awt.*
import java.awt.geom.AffineTransform
import javax.imageio.ImageIO
import javax.swing.JPanel
import kotlin.math.PI

val customComponents = listOf(
  CustomComponent(),
  CustomNotDisposingComponent(),
  CustomShapeComponent(),
  CustomGradientComponent(),
  CustomClippingComponent()
)

class CustomButtonsComponent : JPanel() {

  override fun toString(): String = this::class.simpleName!!

  override fun paintComponent(g: Graphics) {
    super.paintComponent(g)

    val gg = g.create()

    gg.translate(5, 15)

    val g1 = gg.create()
    g1.color = Color(0xbb0000)
    g1.fillRect(0, 0, 60, 60)

    val g2 = gg.create()
    g2.color = Color(0x00bb00)
    g2.fillRect(30, 30, 70, 70)

    g1.color = Color(0x0000bb)
    g1.fillRect(40, 30, 80, 80)
  }
}

class CustomComponent : JPanel() {

  override fun toString(): String = this::class.simpleName!!

  override fun paintComponent(g: Graphics) {
    super.paintComponent(g)

    val g1 = g.create() as Graphics2D
    g1.scale(2.0, 2.0)
    g1.color = Color(0xbb0000)
    g1.fillRect(0, 0, 60, 60)

    val g2 = g.create() as Graphics2D
    g2.scale(2.0, 2.0)
    try {
      g2.color = Color(0x00bb00)
      g2.fillRect(30, 30, 70, 70)
    }
    finally {
      g2.dispose()
    }

    g1.color = Color(0x0000bb)
    g1.fillRect(40, 30, 80, 80)
    g1.dispose()

    val g3 = g.create() as Graphics2D
    g3.scale(2.0, 2.0)
    g3.color = Color(0xbbbb00)
    g3.fillOval(120, 30, 50, 80)

    g3.drawImage(pigImage, AffineTransform(1.0, -0.5, -0.5, 1.0, 200.0, 30.0), null)

    g3.composite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.7f)
    g3.copyArea(20, 20, 100, 20, 50, 10)
    g3.composite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER)

    g3.color = Color(0x00bbbb)
    g3.fillPolygon(
      intArrayOf(70, 70, 50),
      intArrayOf(100, 120, 120),
      3
    )

    g3.drawPolyline(
      intArrayOf(50, 50, 70),
      intArrayOf(120, 100, 100),
      3
    )

    g3.transform = AffineTransform(0.5, 0.0, 0.0, 0.5, 300.0, 300.0)
    g3.drawImage(pigImage, 0, 0, null)

    g3.transform = AffineTransform(1.0, 0.0, 0.0, 1.0, 500.0, 500.0)
    g3.background = Color(0xbb00bb)
    g3.clearRect(0, 0, 30, 15)

    g3.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_OFF)
    g3.drawString("abc def", 0, 30)

    g3.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HBGR)
    g3.drawString("abc def", 0, 60)
  }

  companion object {

    private val pigImage by lazy {
      ImageIO.read(
        CustomComponent::class.java.getResource("/Pig.gif"))
    }
  }
}

/** A component that doesn't dispose. */
class CustomNotDisposingComponent : JPanel() {

  override fun toString(): String = this::class.simpleName!!

  override fun paintComponent(g: Graphics) {
    super.paintComponent(g)

    val gg = g.create()

    gg.translate(5, 15)

    val g1 = gg.create()
    g1.color = Color(0xbb0000)
    g1.fillRect(0, 0, 60, 60)

    val g2 = gg.create()
    g2.color = Color(0x00bb00)
    g2.fillRect(30, 30, 70, 70)

    g1.color = Color(0x0000bb)
    g1.fillRect(40, 30, 80, 80)
  }
}

class CustomShapeComponent : JPanel() {

  override fun toString(): String = this::class.simpleName!!

  override fun paintComponent(g: Graphics) {
    super.paintComponent(g)

    val gg = g.create() as Graphics2D

    gg.translate(5, 15)

    gg.scale(50.0, 50.0)

    val g1 = gg.create()
    g1.color = Color(0xbb0000)
    g1.drawRect(0, 0, 6, 6)

    val g2 = gg.create() as Graphics2D
    g2.stroke = BasicStroke(1.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER, 10.0f)
    g2.color = Color(0x00bb00)
    g2.drawLine(3, 3, 3, 7)
    g2.drawLine(3, 7, 7, 7)
    g2.drawLine(7, 7, 7, 3)
    g2.drawLine(7, 3, 3, 3)
  }
}

class CustomGradientComponent : JPanel() {

  override fun toString(): String = this::class.simpleName!!

  override fun paintComponent(g: Graphics) {
    super.paintComponent(g)

    val ggg = g.create()

    val gg = ggg.create() as Graphics2D
    val bounds = this.bounds
    gg.paint = GradientPaint(
      bounds.minX.toFloat(), bounds.minY.toFloat(),
      Color(0xCC, 0, 0),
      bounds.maxX.toFloat(), bounds.maxY.toFloat(),
      Color(0, 0xCC, 0)
    )
    gg.fill(bounds)

    ggg.translate(5, 15)

    val g1 = ggg.create() as Graphics2D
    g1.paint = GradientPaint(
      0.0f, 0.0f,
      Color(0xCC, 0xCC, 0),
      60.0f, 0.0f,
      Color(0, 0xCC, 0xCC)
    )
    g1.fillRect(0, 0, 60, 60)

    val g2 = ggg.create() as Graphics2D
    g2.paint = GradientPaint(
      30.0f, 30.0f,
      Color(0xCC, 0, 0xCC),
      30.0f, 30.0f + 70,
      Color(0, 0, 0)
    )
    g2.fillRect(30, 30, 70, 70)

    g1.color = Color(0x0000bb)
    g1.fillRect(40, 30, 80, 80)
  }
}

class CustomClippingComponent : JPanel() {

  override fun toString(): String = this::class.simpleName!!

  override fun paintComponent(g: Graphics) {
    super.paintComponent(g)

    val gg = g.create()

    gg.translate(5, 15)

    val g1 = gg.create()
    g1.setClip(0, 10, 46, 56)
    g1.color = Color(0xbb0000)
    g1.fillRect(0, 0, 60, 60)

    val g2 = gg.create()
    g2.color = Color(0x00bb00)
    g2.fillRect(30, 30, 70, 70)

    g1.color = Color(0x0000bb)
    g1.fillRect(40, 30, 80, 80)

    gg.translate(105, 15)

    val g11 = gg.create() as Graphics2D
    g11.rotate(PI / 4)

    g11.setClip(0, 10, 46, 56)
    g11.color = Color(0xbb0000)
    g11.fillRect(0, 0, 60, 60)

    val g22 = gg.create()
    g22.color = Color(0x00bb00)
    g22.fillRect(30, 30, 70, 70)

    g11.color = Color(0x0000bb)
    g11.fillRect(40, 30, 80, 80)
  }
}
