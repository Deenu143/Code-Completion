package org.deenu.code.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.deenu.code.databinding.ActivityMainBinding
import org.deenu.code.fragments.EditorFragment

class MainActivity : AppCompatActivity() {

    companion object {
        @JvmField val TAG: String = MainActivity::class.java.simpleName
    }
	
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState) 
		setView()
    }

    private fun setView() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
		               		
        val editorFragment = EditorFragment()
        if (supportFragmentManager.findFragmentByTag(EditorFragment.TAG) == null) {
            supportFragmentManager.beginTransaction()
                .replace(binding.fragmentContainerView.getId(), editorFragment, EditorFragment.TAG)
                .commit()
        }
    } 
	
	override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
    }
}
