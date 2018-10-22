Ext.define('HouseAgentSystem.view.trade.TreeFilter', {
    filterByText: function(text) {
        this.filterBy(text, 'text');
    },
    /**
     * 根据字符串过滤所有的节点，将不符合条件的节点进行隐藏.
     * @param 查询字符串.
     * @param 要查询的列.
     */
    filterBy: function(text, by) {
 
        this.clearFilter();
 
        var view = this.getView(),
            me = this,
            nodesAndParents = [];
 
        // 找到匹配的节点并展开.
        // 添加匹配的节点和他们的父节点到nodesAndParents数组.
        this.getRootNode().cascadeBy(function(tree, view) {
            var currNode = this;
 
            if (currNode && currNode.data[by] && currNode.data[by].toString().toLowerCase().indexOf(text.toLowerCase()) > -1) {
                me.expandPath(currNode.getPath());
 
                while (currNode.parentNode) {
                    nodesAndParents.push(currNode.id);
                    currNode = currNode.parentNode;
                }
            }
        }, null, [me, view]);
 
        // 将不在nodesAndParents数组中的节点隐藏
        this.getRootNode().cascadeBy(function(tree, view) {
            var uiNode = view.getNodeByRecord(this);
 
            if (uiNode && !Ext.Array.contains(nodesAndParents, this.id)) {
                Ext.get(uiNode).setDisplayed('none');
            }
        }, null, [me, view]);
    },
 
 
    clearFilter: function() {
        var view = this.getView();
 
        this.getRootNode().cascadeBy(function(tree, view) {
            var uiNode = view.getNodeByRecord(this);
 
            if (uiNode) {
                Ext.get(uiNode).setDisplayed('table-row');
            }
        }, null, [this, view]);
    }
});