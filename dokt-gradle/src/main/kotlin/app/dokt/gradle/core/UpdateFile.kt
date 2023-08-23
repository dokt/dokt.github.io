package app.dokt.gradle.core

import app.dokt.generator.building.FileUpdater
import org.gradle.api.file.RegularFileProperty
import org.gradle.api.provider.Property
import org.gradle.api.tasks.*
import kotlin.reflect.KClass

@CacheableTask
abstract class UpdateFile(type: KClass<out UpdateFile>) : LoggableTask(type) {
    @get:OutputFile // Output is required at minimum
    abstract val file: RegularFileProperty

    @get:Input
    abstract val modified: Property<Long>

    private lateinit var updater: FileUpdater

    override fun getDescription() = "Update ${updater.file.name} file."

    protected fun init(updater: FileUpdater) {
        this.updater = updater
        modified.set(updater.modified)
        file.set(updater.file)
    }

    @TaskAction
    fun update() {
        lifecycle { "Updating $file" }
        updater.update()
    }
}