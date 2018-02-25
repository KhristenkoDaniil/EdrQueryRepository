package ua.dnigma.edrquery.callback;

/**
 * Created by Даниил on 25.02.2018.
 */

public interface OnViewDeclarationCallback {

    public void onSucssesWebviewDeclaration(String url);
    public void onFailureWebviewDeclaration(String noLinkPDF);

}
