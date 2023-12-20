package org.deenu.code.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import org.deenu.code.databinding.FragmentEditorBinding

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
        setViews()
    }
	
    private fun setToolbar() {
        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolBar)
    }
	
    private fun setViews() { }
	    	
    override fun onResume() {
        super.onResume()
        setToolbar()
        setViews()
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}
