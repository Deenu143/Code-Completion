package org.deenu.code.editor

import io.github.rosemoe.sora.widget.CodeEditor
import io.github.rosemoe.sora.widget.component.EditorTextActionWindow

class NoOpTextActionWindow(editor: CodeEditor) : EditorTextActionWindow(editor) {

    /**
     * Create a panel for the given editor
     *
     * @param editor Target editor
     */
    init {
        // No need for additional initialization, super class constructor handles it
    }

    override fun show() {
        // do nothing
    }
}
