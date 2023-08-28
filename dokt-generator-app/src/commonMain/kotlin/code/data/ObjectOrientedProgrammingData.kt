/**
 * Object-oriented programming (OOP) data transfer objects
 */
package app.dokt.generator.code.data

import app.dokt.generator.code.CodeFile
import app.dokt.generator.code.Method
import app.dokt.generator.code.TypeDef
import app.dokt.generator.code.TypeRef
import app.dokt.generator.code.Variable
import kotlinx.serialization.Serializable

@Serializable
data class CodeFileData(
    override val types: List<TypeDef>,
    override val path: String,
    override val name: String,
    override val packageName: String
) : CodeFile

@Serializable
data class MethodData(
    override val name: String,
    override val parameters: List<Variable> = emptyList(),
    override val packageName: String = ""
) : Method {
    constructor(name: String, packageName: String = "", vararg parameters: Variable) :
            this(name, parameters.toList(), packageName)
}

@Serializable
data class TypeDefData(
    override val name: String,
    override val packageName: String,
    override val implements: List<TypeRef>,
    override val isData: Boolean,
    override val isEnumeration: Boolean,
    override val isInterface: Boolean,
    override val isValue: Boolean,
    override val methods: List<Method>,
    override val primaryConstructor: List<Variable>,
    override val properties: List<Variable>
) : TypeDef
