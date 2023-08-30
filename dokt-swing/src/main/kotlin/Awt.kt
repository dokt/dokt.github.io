@file:Suppress("unused")

package app.dokt.ui.swing

import app.dokt.common.Rectangle
import java.awt.Component
import java.awt.GraphicsEnvironment
import java.awt.GridBagConstraints
import java.awt.Robot
import java.awt.Toolkit
import java.awt.event.ComponentAdapter
import java.awt.event.ComponentEvent
import java.awt.event.KeyEvent
import javax.swing.KeyStroke
import javax.swing.SwingUtilities

val ROBOT = Robot()
val defaultScreenSize get() = Toolkit.getDefaultToolkit().screenSize!!
val localGraphicsEnvironment by lazy { GraphicsEnvironment.getLocalGraphicsEnvironment()!! }

fun bag(x: Int, y: Int, width: Int = 1) = GridBagConstraints().apply {
    gridwidth = width
    gridx = x
    gridy = y
}

/** https://stackoverflow.com/questions/2192291/how-to-get-screen-position-of-a-swing-element */
val Component.boundsOnScreen: Rectangle get() = locationOnScreen.run { Rectangle(x, y, width, height) }

fun keyStroke(keyCode: Int, modifiers: Int = 0) = KeyStroke.getKeyStroke(keyCode, modifiers)!!

val KeyEvent.isDelete get() = keyCode == KeyEvent.VK_DELETE

fun Component.boundsIn(ancestor: Component): Rectangle = SwingUtilities.convertRectangle(this, bounds, ancestor)
fun Component.listenBounds(listener: () -> Unit) = addComponentListener(object : ComponentAdapter() {
    override fun componentResized(e: ComponentEvent?) = listener()
    override fun componentMoved(e: ComponentEvent?) = listener()
})
fun Component.listenVisibility(shown: () -> Unit, hidden: () -> Unit = {}) =
    addComponentListener(object : ComponentAdapter() {
        override fun componentHidden(e: ComponentEvent?) = hidden()
        override fun componentShown(e: ComponentEvent?) = shown()
    })