package org.deenu.code;

import io.github.rosemoe.sora.widget.component.EditorAutoCompletion;
import kotlin.jvm.internal.Reflection;

public class Test {
	Test() {
		try {
			Reflection.getOrCreateKotlinClass(EditorAutoCompletion.class).getClass().getField("adapter");
		} catch (NoSuchFieldException e) {
		}
	}
}