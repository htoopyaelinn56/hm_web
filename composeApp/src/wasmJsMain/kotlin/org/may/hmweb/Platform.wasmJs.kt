package org.may.hmweb

class WasmPlatform: Platform {
    override val name: String = "Web with Kotlin/Wasm"
}

actual fun getPlatform(): Platform = WasmPlatform()

actual fun getScreenWidth(): Int = js("window.innerWidth")