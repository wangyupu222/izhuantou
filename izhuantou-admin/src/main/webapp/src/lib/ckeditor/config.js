/**
 * @license Copyright (c) 2003-2018, CKSource - Frederico Knabben. All rights reserved.
 * For licensing, see https://ckeditor.com/legal/ckeditor-oss-license
 */

CKEDITOR.editorConfig = function( config ) {
	// Define changes to default configuration here. For example:
	// config.language = 'fr';
	// config.uiColor = '#AADC6E';
    config.height = 272; //高度
    config.toolbar = [

        ['Source', '-', 'Preview', '-', 'Templates'],

        ['Cut', 'Copy', 'Paste', 'PasteText', 'PasteFromWord', '-','SpellChecker', 'Scayt'],

        ['Undo', 'Redo', '-', 'Find', 'Replace', '-', 'SelectAll','RemoveFormat','Form'],
        ['Link', 'Unlink', 'Anchor'],

        '/',

        ['Bold', 'Italic', 'Underline', 'Strike', '-', 'Subscript','Superscript'],

        ['NumberedList', 'BulletedList', '-', 'Outdent', 'Indent','Blockquote'],

        ['JustifyLeft', 'JustifyCenter', 'JustifyRight', 'JustifyBlock'],

        ['Image', 'Table', 'HorizontalRule', 'PageBreak'],

        '/',

        ['Styles', 'Format', 'Font', 'FontSize'],

        ['TextColor', 'BGColor'],

        ['Maximize', 'ShowBlocks', '-']
    ];

};
