package app.dokt.generator.code.psi

import app.dokt.infra.Logger
import org.jetbrains.kotlin.cli.jvm.compiler.*
import org.jetbrains.kotlin.com.intellij.openapi.util.Disposer
import org.jetbrains.kotlin.com.intellij.psi.*
import org.jetbrains.kotlin.com.intellij.testFramework.LightVirtualFile
import org.jetbrains.kotlin.config.CompilerConfiguration
import org.jetbrains.kotlin.idea.KotlinFileType
import org.jetbrains.kotlin.psi.*

/** PSI environment */
open class Environment(
    // TODO different environments for JVM, JS, Native and WASM
    environment: KotlinCoreEnvironment = KotlinCoreEnvironment.createForProduction(
        Disposer.newDisposable(),
        CompilerConfiguration(),
        EnvironmentConfigFiles.JVM_CONFIG_FILES
    )
) : Logger({}) {
    private val project = environment.project

    val documentManager by lazy { PsiDocumentManager.getInstance(project)!! }

    val factory by lazy { KtPsiFactory(project) }

    private val fileFactory by lazy { PsiFileFactory.getInstance(project) }

    private val manager = PsiManager.getInstance(project)

    private fun context(file: PsiFile?) = Context(this, file as KtFile)

    /*fun parse(filename: String, text: String) =
        context(manager.findFile(LightVirtualFile(filename, KotlinFileType.INSTANCE, text)))*/

    fun parseKt(text: String) = create("tmp.kt", text)

    fun parseKts(text: String) = create("tmp.kts", text)

    fun create(filename: String, text: String) =
        context(fileFactory.createFileFromText(filename, KotlinFileType.INSTANCE, text))
}
