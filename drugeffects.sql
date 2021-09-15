SELECT COUNT(DISTINCT o.patientid)
FROM observations o, drugs d
WHERE d.name = 'tocilizumab'
AND d.patientid = o.patientid
AND o.date_time > MIN(d.date_time)
AND o.freetext LIKE '%dizz%'
;;
