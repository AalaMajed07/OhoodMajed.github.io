function project4_bipartite()
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
    % Create graph object
    G = graph(adj);
    %% ************ Run Bipartite Check and Coloring **************
    [isBipartite, colors] = myBipartiteCheck(G, n);
   
    %% ******************* Output Results ****************************
    fprintf('**** Project 4 ****\n');
    if isBipartite
        fprintf(' True (The graph is Bipartite)\n');
        fprintf('Valid 2-Coloring (0 and 1):\n');
        fprintf('%-10s %-10s\n', 'Vertex', 'Color');
        for i = 1:n
            fprintf('%-10d %-10d\n', i, colors(i));
        end
    else
        fprintf('Output: False (The graph is NOT Bipartite)\n');
    end

    % Graph  
    nodeLabels = string(1:n);
    G.Nodes.Name = nodeLabels';
    figure;
    % Plot the graph
    p = plot(G, 'NodeLabel', G.Nodes.Name, 'Layout', 'force');
    p.MarkerSize = 8;
    
    % Apply Colors
    nodeColors = repmat([0.5 0.5 0.5], n, 1); % Default Grey
    
    if isBipartite
        % Color 0 = Blue, Color 1 = Red
        for i = 1:n
            if colors(i) == 0
                nodeColors(i,:) = [0 0.447 0.741];   % Blue
            elseif colors(i) == 1
                nodeColors(i,:) = [0.850 0.325 0.098]; % Red
            end
        end
        title('Bipartite Graph Coloring (Blue/Red)');
    else
        % Non-Bipartite graph: all nodes remain Grey
        title('Non-Bipartite Graph (No 2-Coloring Found)');
        text(max(p.XData)*1.1, max(p.YData)*1.1, 'Grey Nodes = Not Bipartite', 'Color', [0.5 0.5 0.5], 'FontSize', 10);
    end
    p.NodeColor = nodeColors;
end % End of main function
%% ********************  (BIPARTITE CHECK + 2-COLORING ) ********************
function [isBip, color] = myBipartiteCheck(G, n)
    % Uses BFS to attempt a 2-coloring.
    % n is the number of nodes.
    
    color = -1 * ones(n,1);  % -1 = not colored yet
    isBip = true;
    
    % Loop through all nodes to handle disconnected components
    for startNode = 1:n
        if color(startNode) ~= -1
            continue; % Already colored
        end
        % Initialize BFS for the current component
        queue = startNode;
        color(startNode) = 0;  % Start coloring with 0
        
        while ~isempty(queue)
            v = queue(1);
            queue(1) = []; % Dequeue
            
            neigh = neighbors(G, v);
            
            for k = 1:length(neigh)
                u = neigh(k); % Neighbor of v
                
                if color(u) == -1
                    % If uncolored, assign opposite color
                    color(u) = 1 - color(v);
                    queue(end+1) = u; % Enqueue
                elseif color(u) == color(v)
                    % If neighbor has same color → not bipartite
                    isBip = false;
                    % Return immediately as coloring failed
                    return;
                end
            end
        end
    end
end