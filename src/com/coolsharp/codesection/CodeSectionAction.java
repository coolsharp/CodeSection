package com.coolsharp.codesection;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.LangDataKeys;
import com.intellij.openapi.actionSystem.PlatformDataKeys;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.editor.Editor;
import com.intellij.psi.*;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.util.IncorrectOperationException;

public class CodeSectionAction extends AnAction {
    // [final/static_property]====================[START]===================[final/static_property]
    // [final/static_property]=====================[END]====================[final/static_property]
    // [private/protected/public_property]========[START]=======[private/protected/public_property]
    // [private/protected/public_property]=========[END]========[private/protected/public_property]
    // [interface/enum/inner_class]===============[START]==============[interface/enum/inner_class]
    // [interface/enum/inner_class]================[END]===============[interface/enum/inner_class]
    // [inherited/listener_method]================[START]===============[inherited/listener_method]

    /**
     * 버튼 클릭시 발생하는 이벤트
     * @param event 이벤트
     */
    @Override
    public void actionPerformed(AnActionEvent event) {
        // PsiClass를 추출함
        PsiClass psiClass = getPsiClassFromEvent(event);

        // CodeSection을 생성함
        generateCodeSection(psiClass);
    }

    // [inherited/listener_method]=================[END]================[inherited/listener_method]
    // [life_cycle_method]========================[START]=======================[life_cycle_method]
    // [life_cycle_method]=========================[END]========================[life_cycle_method]
    // [private_method]===========================[START]==========================[private_method]

    /**
     * 코드섹션 생성
     * @param psiClass psiClass
     */
    private void generateCodeSection(final PsiClass psiClass) {
        new WriteCommandAction.Simple(psiClass.getProject(), psiClass.getContainingFile()) {
            @Override
            protected void run() throws Throwable {
                generate(psiClass);
            }
        }.execute();
    }

    /**
     * AnActionEvent로부터 PsiClass를 추출함
     * @param event
     * @return
     */
    private PsiClass getPsiClassFromEvent(AnActionEvent event) {
        PsiFile psiFile = event.getData(LangDataKeys.PSI_FILE);
        Editor editor = event.getData(PlatformDataKeys.EDITOR);

        if (psiFile == null || editor == null) {
            return null;
        }

        int offset = editor.getCaretModel().getOffset();
        PsiElement element = psiFile.findElementAt(offset);

        return PsiTreeUtil.getParentOfType(element, PsiClass.class);
    }

    /**
     * 주석 추가
     * @param psiClass
     * @param elementFactory
     * @param comment
     */
    private void addComment(final PsiClass psiClass, PsiElementFactory elementFactory, String comment) {
        psiClass.addBefore(elementFactory.createCommentFromText(comment, psiClass), psiClass.getFirstChild());
    }

    /**
     * 코드섹션 생성
     * @param psiClass psiClass
     */
    private void generate(final PsiClass psiClass) {
        try {
            PsiElementFactory elementFactory = JavaPsiFacade.getElementFactory(psiClass.getProject());
            if (null != elementFactory) {
                addComment(psiClass, elementFactory, "// [final/static_property]====================[START]===================[final/static_property]");
                addComment(psiClass, elementFactory, "// [final/static_property]=====================[END]====================[final/static_property]");
                addComment(psiClass, elementFactory, "// [private/protected/public_property]========[START]=======[private/protected/public_property]");
                addComment(psiClass, elementFactory, "// [private/protected/public_property]=========[END]========[private/protected/public_property]");
                addComment(psiClass, elementFactory, "// [interface/enum/inner_class]===============[START]==============[interface/enum/inner_class]");
                addComment(psiClass, elementFactory, "// [interface/enum/inner_class]================[END]===============[interface/enum/inner_class]");
                addComment(psiClass, elementFactory, "// [inherited/listener_method]================[START]===============[inherited/listener_method]");
                addComment(psiClass, elementFactory, "// [inherited/listener_method]=================[END]================[inherited/listener_method]");
                addComment(psiClass, elementFactory, "// [life_cycle_method]========================[START]=======================[life_cycle_method]");
                addComment(psiClass, elementFactory, "// [life_cycle_method]=========================[END]========================[life_cycle_method]");
                addComment(psiClass, elementFactory, "// [private_method]===========================[START]==========================[private_method]");
                addComment(psiClass, elementFactory, "// [private_method]============================[END]===========================[private_method]");
                addComment(psiClass, elementFactory, "// [public_method]============================[START]===========================[public_method]");
                addComment(psiClass, elementFactory, "// [public_method]=============================[END]============================[public_method]");
                addComment(psiClass, elementFactory, "// [get/set]==================================[START]=================================[get/set]");
                addComment(psiClass, elementFactory, "// [get/set]===================================[END]==================================[get/set]");
            }
        }
        catch (IncorrectOperationException e) {
        }
        catch (Throwable throwable) {
        }
    }

    // [private_method]============================[END]===========================[private_method]
    // [public_method]============================[START]===========================[public_method]
    // [public_method]=============================[END]============================[public_method]
    // [get/set]==================================[START]=================================[get/set]
    // [get/set]===================================[END]==================================[get/set]

}
