function project3_bridges()
    %% ******************* Read the Graph**************************
    filename = 'graph.csv';
    if ~isfile(filename)
        error('File "%s" not found. Please create the CSV file first.', filename);
    end
    try
        adj = readmatrix(filename);
    catch
        adj = csvread(filename);
    end
    [rows, cols] = size(adj);
    if rows ~= cols
        error('Adjacency matrix must be square.');
    end
    n = rows;
    % Create graph object immediately for connectivity functions
    G = graph(adj);

    %% ******************* Run Functions **************************
    bridges = findBridges(G);
    cut_vertex_indices = findCutVertices(G); % Renamed for consistency
    %% ******************* Output Results ****************************
    fprintf('**** Project (3) *****\n');

    % Output Cut-Vertices
    fprintf('Cut-Vertices: \n');
    if isempty(cut_vertex_indices)
        disp('None');
    else
        disp(cut_vertex_indices);
    end
    % Output Bridges
    fprintf('Bridges (Edges): \n');
    if isempty(bridges)
        disp('None');
    else
        % Display bridges as "u -- v"
        for k = 1:size(bridges, 1)
            fprintf('%d -- %d\n', bridges(k, 1), bridges(k, 2));
        end
    end
    %% Graph Simplified    
    nodeLabels = string(1:n);
    G.Nodes.Name = nodeLabels';
    figure;
    plot(G, 'NodeLabel', G.Nodes.Name, 'Layout', 'force');
    title('TheGraph ');
end

%% ******** FUNCTION (FIND BRIDGES) - Connectivity Check ******
function bridges = findBridges(G)
    % Finds bridges by iteratively removing each edge and checking if the number of connected components increases.
    bridges = [];
    E = table2array(G.Edges(:,1));
    for i = 1:size(E,1)
        u = E(i,1);
        v = E(i,2);
        
        % Remove the edge (u, v)
        G2 = rmedge(G, u, v);
        
        % Check connectivity: if number of components increases → it's a bridge
        % conncomp returns the component ID for each node. max(conncomp) gives the number of components.
        if max(conncomp(G2)) > 1
            bridges(end+1,:) = [u v]; %#ok<AGROW>
        end
    end
end
%% *** FUNCTION (FIND CUT VERTICES) - Connectivity Check ****
function cutV = findCutVertices(G)
    % Finds cut vertices by iteratively removing each vertex and checking 
    % if the graph becomes disconnected.
    cutV = [];
    n = numnodes(G);
    for v = 1:n
        % Remove the vertex v and its incident edges
        G2 = rmnode(G, v);
        
        % If graph becomes disconnected → it's a cut vertex
        if max(conncomp(G2)) > 1
            cutV(end+1) = v; %#ok<AGROW>
        end
    end
end