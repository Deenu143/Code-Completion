package org.deenu.code.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import org.deenu.code.databinding.FragmentEditorBinding
import io.github.rosemoe.sora.langs.textmate.registry.FileProviderRegistry
import io.github.rosemoe.sora.langs.textmate.registry.provider.AssetsFileResolver
import io.github.rosemoe.sora.langs.textmate.registry.model.ThemeModel
import io.github.rosemoe.sora.langs.textmate.registry.GrammarRegistry
import org.eclipse.tm4e.core.registry.IThemeSource
import io.github.rosemoe.sora.langs.textmate.registry.ThemeRegistry
import io.github.rosemoe.sora.langs.textmate.TextMateColorScheme
import io.github.rosemoe.sora.langs.textmate.TextMateLanguage
import io.github.rosemoe.sora.text.ContentIO
import java.io.IOException
import android.graphics.Typeface
import org.deenu.code.editor.NoOpTextActionWindow
import org.deenu.code.editor.EditorCodeCompletion
import io.github.rosemoe.sora.widget.component.EditorTextActionWindow
import io.github.rosemoe.sora.widget.component.EditorAutoCompletion

class EditorFragment : Fragment() {
	
    companion object {
        @JvmField val TAG: String = EditorFragment::class.java.simpleName	
    }
	
    private lateinit var binding: FragmentEditorBinding
    	
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
	
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEditorBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }
	
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setToolbar()        
		loadDefaultThemes()
        loadDefaultLanguages()
		ensureTextmateTheme()
		setViews()		
    }
	
    private fun setToolbar() {
        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolBar)
    }
	
    private fun setViews() { 			
		binding.editor.typefaceText = Typeface.createFromAsset(requireContext().assets, "gabarito.ttf")  
        val language = TextMateLanguage.create(
            "source.java", true
        )
		binding.editor.replaceComponent(EditorTextActionWindow::class.java, NoOpTextActionWindow(binding.editor))
        binding.editor.replaceComponent(EditorAutoCompletion::class.java, EditorCodeCompletion(binding.editor))
	    binding.editor.setEditorLanguage(language)
		openAssetsFile("samples/sample.txt")
	}
	
	private /*suspend*/ fun loadDefaultThemes() /*= withContext(Dispatchers.IO)*/ {

        //add assets file provider
        FileProviderRegistry.getInstance().addFileProvider(
            AssetsFileResolver(
                requireContext().assets
            )
        )

        val themes = arrayOf("darcula", "abyss", "quietlight")
        val themeRegistry = ThemeRegistry.getInstance()
        themes.forEach { name ->
            val path = "textmate/$name.json"
            themeRegistry.loadTheme(
                ThemeModel(
                    IThemeSource.fromInputStream(
                        FileProviderRegistry.getInstance().tryGetInputStream(path), path, null
                    ), name
                ).apply {
                    if (name != "quietlight") {
                        isDark = true
                    }
                }
            )
        }

        themeRegistry.setTheme("quietlight")
    }

    private /*suspend*/ fun loadDefaultLanguages() /*= withContext(Dispatchers.Main)*/ {
        GrammarRegistry.getInstance().loadGrammars("textmate/languages.json")
    }
	
	private fun ensureTextmateTheme() {
        val editor = binding.editor
        var editorColorScheme = editor.colorScheme
        if (editorColorScheme !is TextMateColorScheme) {
            editorColorScheme = TextMateColorScheme.create(ThemeRegistry.getInstance())
            editor.colorScheme = editorColorScheme
        }
    }
	
	private fun openAssetsFile(name: String) {
        Thread {
            try {
                val text = ContentIO.createFrom(requireContext().assets.open(name))
                (requireActivity() as AppCompatActivity).runOnUiThread {
                    binding.editor.setText(text, null)                  
                }
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }.start()        
    }
	
    override fun onResume() {
        super.onResume()
        setToolbar()        
		loadDefaultThemes()
        loadDefaultLanguages()
		ensureTextmateTheme()
		setViews()
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}
