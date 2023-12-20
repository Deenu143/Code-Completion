package org.deenu.code.editor

import io.github.rosemoe.sora.widget.CodeEditor
import io.github.rosemoe.sora.widget.component.EditorAutoCompletion
import io.github.rosemoe.sora.lang.completion.CompletionItem
import io.github.rosemoe.sora.widget.component.CompletionLayout
import android.widget.ListView
import io.github.rosemoe.sora.widget.component.EditorCompletionAdapter
import androidx.annotation.NonNull
import androidx.annotation.Nullable

class EditorCodeCompletion(editor: CodeEditor) : EditorAutoCompletion(editor) {

	private val TAG: String = EditorCodeCompletion::class.java.simpleName
    private val mEditor: CodeEditor = editor
    private var mLayout: CompletionLayout? = null
    private var mListView: ListView? = null
    private var mAdapter: EditorCompletionAdapter = adapter
    private val mItems: MutableList<CompletionItem> = ArrayList()

    override fun setLayout(@NonNull layout: CompletionLayout) {
        super.setLayout(layout)
        mLayout = layout
        mListView = layout.completionList as? ListView
        mListView?.adapter = mAdapter
    }	   
	
	override fun requireCompletion() {
		if (cancelShowUp || !isEnabled()) {
        return
        }
		val text = mEditor.text
        if (text.cursor.isSelected || checkNoCompletion()) {
            hide()
            return
        }  
	}				    
}
