@file:Suppress("unused", "MemberVisibilityCanBePrivate")

package app.dokt.ui.swing

import io.github.oshai.kotlinlogging.KotlinLogging
import java.time.Duration
import java.time.Instant
import javax.swing.JFrame
import javax.swing.JInternalFrame
import javax.swing.SwingWorker

class Task<T>(private val job: () -> T, private val ui: (T) -> Unit, val name: String = "task")
    : SwingWorker<T, Nothing>() {
    val logger = KotlinLogging.logger {}

    init { execute() }

    override fun doInBackground(): T {
        logger.trace { "Starting $name" }
        val started = Instant.now()
        val value = job()
        // TODO use kotlinx-datetime
        logger.debug { "Done $name in ${Duration.between(started, Instant.now())}."}
        return value
    }

    override fun done() { if (!isCancelled) ui(get()) }
}

fun <T> JFrame.task(job: () -> T, ui: (T) -> Unit) = Task(job, ui, title)
fun <T> JInternalFrame.task(job: () -> T, ui: (T) -> Unit) = Task(job, ui, title)