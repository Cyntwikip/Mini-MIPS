package Viewer.GUI;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.wb.swt.SWTResourceManager;

import Controller.Assembler2;
import org.eclipse.swt.widgets.Label;

public class Home {

	protected Shell shell;
	
	private static String text = "";
	private static String answerHex = "";
	private static String answerBin = "";

	/**
	 * Launch the application.
	 * @param args
	 */
	public void run() {
		try {
			open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 * @wbp.parser.entryPoint
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(894, 541);
		shell.setText("SWT Application");
		
		StyledText textArea1 = new StyledText(shell, SWT.BORDER);
		textArea1.setBounds(10, 60, 315, 432);
		
		StyledText textArea2 = new StyledText(shell, SWT.BORDER);
		textArea2.setEditable(false);
		textArea2.setBounds(378, 60, 237, 315);
		
		StyledText textArea3 = new StyledText(shell, SWT.BORDER);
		textArea3.setEditable(false);
		textArea3.setBounds(631, 60, 237, 315);
		
		Button button1 = new Button(shell, SWT.NONE);
		button1.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		button1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {	
				text = textArea1.getText();
			
				//System.out.println(text);
				Assembler2 a = new Assembler2();	
				//System.out.println("Run pressed!");
				//System.out.println(text);
				a.compile();
				//textArea2.setText(answer);
				textArea2.setText(answerHex);
				textArea3.setText(answerBin);
			}
		});
		button1.setBounds(558, 403, 152, 64);
		button1.setText("Run");
		
		Label lblTypeYourCode = new Label(shell, SWT.NONE);
		lblTypeYourCode.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		lblTypeYourCode.setBounds(80, 10, 164, 29);
		lblTypeYourCode.setText("Type your code here");
		
		Label lblThisIsThe = new Label(shell, SWT.NONE);
		lblThisIsThe.setText("This is the output");
		lblThisIsThe.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		lblThisIsThe.setBounds(558, 10, 164, 29);

	}
	
	public String getText() {
		return text;
	}
	
	public void setAnswerHex(String answer) {
		answerHex = answer;
	}
	
	public void setAnswerBin(String answer) {
		answerBin = answer;
	}
}
