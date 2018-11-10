package hu.racz.zalan.editor.errordisplay;

import hu.racz.zalan.editor.antlr.generated.*;
import hu.racz.zalan.editor.core.*;
import javax.swing.event.*;
import org.netbeans.modules.parsing.api.*;
import org.netbeans.modules.parsing.spi.*;
import org.netbeans.modules.parsing.spi.Parser;

public class GlslParser extends Parser {

    private Snapshot snapshot;
    private static EditorCaretListener caretListener;

    @Override
    public void parse(Snapshot snpsht, Task task, SourceModificationEvent sme) throws ParseException {
        if (caretListener == null) {
            caretListener = new EditorCaretListener();
            org.netbeans.api.editor.EditorRegistry.lastFocusedComponent().addCaretListener(caretListener);
        }
        this.snapshot = snpsht;
        GlslProcessor.setText(snpsht.getText().toString());
    }

    public static EditorCaretListener getCaretListener() {
        return caretListener;
    }

    @Override
    public Result getResult(Task task) throws ParseException {
        return new GlslEditorParserResult(snapshot);
    }

    @Override
    public void addChangeListener(ChangeListener cl) {

    }

    @Override
    public void removeChangeListener(ChangeListener cl) {

    }

    public static class GlslEditorParserResult extends Result {

        GlslEditorParserResult(Snapshot snapshot) {
            super(snapshot);
        }

        public AntlrGlslParser getGlslParser() throws ParseException {
            return GlslProcessor.getParser();
        }

        @Override
        protected void invalidate() {

        }
    }

}
